package com.learning.user.service.handler;

import com.learning.user.UserInformation;
import com.learning.user.UserInformationRequest;
import com.learning.user.exceptions.UnknownUserException;
import com.learning.user.repository.PortfolioItemRepository;
import com.learning.user.repository.UserRepository;
import com.learning.user.utils.EntityMessageMapper;
import org.springframework.stereotype.Service;

@Service
public class UserInformationRequestHandler {

    private final UserRepository userRepository;
    private final PortfolioItemRepository portfolioItemRepository;

    public UserInformationRequestHandler(UserRepository userRepository, PortfolioItemRepository portfolioItemRepository) {
        this.userRepository = userRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }

    public UserInformation getUserInformation(UserInformationRequest request) {
        var user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UnknownUserException(request.getUserId()));
        var portfolioItems = this.portfolioItemRepository.findAllByUserId(request.getUserId());
        return EntityMessageMapper.toUserInformation(user, portfolioItems);
    }
}
