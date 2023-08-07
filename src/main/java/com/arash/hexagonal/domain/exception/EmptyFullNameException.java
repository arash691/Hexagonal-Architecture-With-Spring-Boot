package com.arash.hexagonal.domain.exception;

/**
 * Created by arash on 26.10.22.
 */
public class EmptyFullNameException extends RuntimeException {
    public EmptyFullNameException() {
        super("FullName is required");
    }

}
