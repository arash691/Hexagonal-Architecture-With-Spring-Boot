package com.arash.hexagonal.domain.entity;

import com.arash.hexagonal.domain.exception.EmptyFullNameException;
import com.arash.hexagonal.domain.exception.EmptyPhoneNumberException;
import com.arash.hexagonal.domain.vo.FullName;
import com.arash.hexagonal.domain.vo.ID;
import com.arash.hexagonal.domain.vo.PhoneNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * Created by arash on 21.10.22.
 */

public class PatientEntityFactoryTest extends EntityFactoryTest {
    @Test
    @DisplayName("givenSimpleId_WhenCreatePatientEntity_ThenPatientEntityIdIsEqualsWithSimpleId")
    public void givenSimpleId_WhenCreatePatientEntity_ThenPatientEntityIdIsEqualsWithSimpleId() {
        Patient patient = Patient.of(id);
        assertEquals(patient.getId(), ID.of(id));
    }

    @Test
    @DisplayName("givenFullNameAndPhoneNumber_WhenCreatePatientEntity_ThenPatientEntityPropertiesValuesAreExpected")
    public void givenFullNameAndPhoneNumber_WhenCreatePatientEntity_ThenPatientEntityPropertiesValuesAreExpected() {
        Patient patient = Patient.of(fullName, phoneNumber);
        assertEquals(patient.getPhoneNumber().getPhoneNumber(), PhoneNumber.of(phoneNumber).getPhoneNumber());
        assertEquals(patient.getFullName().getFullName(), FullName.of(fullName).getFullName());
    }

    @Test
    @DisplayName("givenNullPhoneNumber_WhenCreatePatientEntity_ThenPatientEntityWillThrowsEmptyPhoneNumberException")
    public void givenNullPhoneNumber_WhenCreatePatientEntity_ThenPatientEntityWillThrowsEmptyPhoneNumberException() {
        assertThrowsExactly(EmptyPhoneNumberException.class, () -> Patient.of(fullName, null));
    }

    @Test
    @DisplayName("givenNullFullName_WhenCreatePatientEntity_ThenPatientEntityWillThrowsEmptyFullNameException")
    public void givenNullFullName_WhenCreatePatientEntity_ThenPatientEntityWillThrowsEmptyFullNameException() {
        assertThrowsExactly(EmptyFullNameException.class, () -> Patient.of(null, phoneNumber));
    }

}
