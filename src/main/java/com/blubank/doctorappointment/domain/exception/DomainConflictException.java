package com.blubank.doctorappointment.domain.exception;

/**
 * Created by arash on 29.10.22.
 */

public class DomainConflictException extends RuntimeException {
    public DomainConflictException(String s) {
        super(s);
    }
}
