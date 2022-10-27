package com.blubank.doctorappointment.domain.exception;

/**
 * @author a.ariani
 */
public class DomainNotFoundException extends RuntimeException {
    public DomainNotFoundException(String message) {
        super(message);
    }
}
