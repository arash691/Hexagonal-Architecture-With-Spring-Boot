package com.blubank.doctorappointment.domain.exception;

/**
 * @author a.ariani
 */
public class BusinessDomainException extends RuntimeException{
    public BusinessDomainException(String message) {
        super(message);
    }
}
