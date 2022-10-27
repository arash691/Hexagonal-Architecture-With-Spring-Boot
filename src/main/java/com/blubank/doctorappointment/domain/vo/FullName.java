package com.blubank.doctorappointment.domain.vo;

/**
 * @author a.ariani
 */
public class FullName {
    private final String fullName;


    private FullName(String fullName) {
        this.fullName = fullName;
    }

    public static FullName of(String fullName) {
        return new FullName(fullName);
    }

    public String getFullName() {
        return fullName;
    }
}
