package com.learning.user.exceptions;

public class InsufficientSharesException extends RuntimeException {

    private static final String MESSAGE = "User [id=%d] does not have enough fund to complete the transaction";

    public InsufficientSharesException(Integer userId) {
        super(MESSAGE.formatted(userId));
    }
}
