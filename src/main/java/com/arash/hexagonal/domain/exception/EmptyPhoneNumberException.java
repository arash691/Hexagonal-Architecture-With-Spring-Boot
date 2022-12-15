package com.arash.hexagonal.domain.exception;

/**
 * Created by arash on 26.10.22.
 */

public class EmptyPhoneNumberException extends RuntimeException {
    public EmptyPhoneNumberException(String message) {
        super(message);
    }
}
