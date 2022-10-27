package com.blubank.doctorappointment.domain.exception;

/**
 * @author a.ariani
 */
public class InvalidStartAndEndTimeException extends RuntimeException {
    public InvalidStartAndEndTimeException(String s) {
        super(s);
    }
}
