package com.shop.shopmanagementbackend.userAuth.exception;

public class CredentialMisMatchError extends RuntimeException {
    public CredentialMisMatchError(String message) {
        super(message);
    }
}
