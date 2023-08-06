package com.arash.hexagonal.domain.vo;

import com.arash.hexagonal.domain.exception.NullMedicalNumberException;

/**
 * @author iman hosseinzadeh
 */
public record MedicalNumber(Long value) {
    public MedicalNumber {
        if (value == null) {
            throw new NullMedicalNumberException();
        }
    }
}
