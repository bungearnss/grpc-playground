package com.learning.aggregator.service;

import com.learning.stock.StockPriceRequest;
import com.learning.stock.StockServiceGrpc;
import com.learning.user.StockTradeRequest;
import com.learning.user.StockTradeResponse;
import com.learning.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userClient;

    @GrpcClient("stock-service")
    private StockServiceGrpc.StockServiceBlockingStub stockClient;

    public StockTradeResponse trade(StockTradeRequest request){
        var priceRequest = StockPriceRequest.newBuilder().setTicker(request.getTicker()).build();
        var priceResponse = this.stockClient.getStockPrice(priceRequest);
        var tradeRequest = request.toBuilder().setPrice(priceResponse.getPrice()).build();
        return this.userClient.tradeStock(tradeRequest);
    }
}
