package com.arash.hexagonal.domain.vo;

/**
 * @author a.ariani
 */
public class PhoneNumber {
    private final String phoneNumber;

    private PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static PhoneNumber of(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
