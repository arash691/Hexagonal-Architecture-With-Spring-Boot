package com.arash.hexagonal.domain.exception;

/**
 * @author a.ariani
 */
public class InvalidStartAndEndTimeException extends BusinessDomainException {
    public InvalidStartAndEndTimeException() {
        super("The end time cannot be before the begin time");
    }
}
