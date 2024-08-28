package com.learning.user.service;

import com.learning.user.*;
import com.learning.user.service.handler.StockTradeRequestHandler;
import com.learning.user.service.handler.UserInformationRequestHandler;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserInformationRequestHandler userRequestHandler;
    private final StockTradeRequestHandler tradeRequestHandler;

    public UserService(UserInformationRequestHandler userRequestHandler,
                       StockTradeRequestHandler tradeRequestHandler) {
        this.userRequestHandler = userRequestHandler;
        this.tradeRequestHandler = tradeRequestHandler;
    }

    @Override
    public void getUserInformation(UserInformationRequest request, StreamObserver<UserInformation> responseObserver) {
        log.info("user information for id {}", request.getUserId());
        var userInformation = this.userRequestHandler.getUserInformation(request);
        responseObserver.onNext(userInformation);
        responseObserver.onCompleted();
    }

    @Override
    public void tradeStock(StockTradeRequest request, StreamObserver<StockTradeResponse> responseObserver) {
        log.info("{}", request);
    }

}
