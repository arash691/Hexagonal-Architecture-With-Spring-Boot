package com.arash.hexagonal.domain.vo;

import com.arash.hexagonal.domain.exception.EmptyPhoneNumberException;

/**
 * @author iman hosseinzadeh
 */
public record PhoneNumber(String value) {
    public PhoneNumber {
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new EmptyPhoneNumberException();
        }
    }
}
