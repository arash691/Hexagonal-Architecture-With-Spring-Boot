package com.arash.hexagonal.domain.exception;

/**
 * @author a.ariani
 */
public class NullMedicalNumberException extends RuntimeException {
    public NullMedicalNumberException() {
        super("Medical number is null");
    }
}
