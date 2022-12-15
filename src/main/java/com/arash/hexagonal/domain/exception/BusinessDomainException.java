package com.arash.hexagonal.domain.exception;

/**
 * @author a.ariani
 */
public class BusinessDomainException extends RuntimeException {
    public BusinessDomainException(String message) {
        super(message);
    }
}
