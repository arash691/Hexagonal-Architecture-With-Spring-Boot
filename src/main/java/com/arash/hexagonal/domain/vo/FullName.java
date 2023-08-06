package com.arash.hexagonal.domain.vo;

import com.arash.hexagonal.domain.exception.EmptyFullNameException;

/**
 * @author iman hosseinzadeh
 */
public record FullName(String value) {
    public FullName {
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new EmptyFullNameException();
        }
    }

}
