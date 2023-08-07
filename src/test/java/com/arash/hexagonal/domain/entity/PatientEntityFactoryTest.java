package com.arash.hexagonal.domain.entity;

import com.arash.hexagonal.domain.exception.EmptyFullNameException;
import com.arash.hexagonal.domain.exception.EmptyPhoneNumberException;
import com.arash.hexagonal.domain.vo.FullName;
import com.arash.hexagonal.domain.vo.Id;
import com.arash.hexagonal.domain.vo.PhoneNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * Created by arash on 21.10.22.
 */
class PatientEntityFactoryTest extends EntityFactoryTest {
    @Test
    @DisplayName("givenSimpleId_WhenCreatePatientEntity_ThenPatientEntityIdIsEqualsWithSimpleId")
    void givenSimpleId_WhenCreatePatientEntity_ThenPatientEntityIdIsEqualsWithSimpleId() {
        Patient patient = Patient.of(id);
        assertEquals(patient.getId(), new Id(id));
    }

    @Test
    @DisplayName("givenFullNameAndPhoneNumber_WhenCreatePatientEntity_ThenPatientEntityPropertiesValuesAreExpected")
    void givenFullNameAndPhoneNumber_WhenCreatePatientEntity_ThenPatientEntityPropertiesValuesAreExpected() {
        Patient patient = Patient.of(fullName, phoneNumber);
        assertEquals(patient.getPhoneNumber().value(), new PhoneNumber(phoneNumber).value());
        assertEquals(patient.getFullName().value(), new FullName(fullName).value());
    }

    @Test
    @DisplayName("givenNullPhoneNumber_WhenCreatePatientEntity_ThenPatientEntityWillThrowsEmptyPhoneNumberException")
    void givenNullPhoneNumber_WhenCreatePatientEntity_ThenPatientEntityWillThrowsEmptyPhoneNumberException() {
        assertThrowsExactly(EmptyPhoneNumberException.class, () -> Patient.of(fullName, null));
    }

    @Test
    @DisplayName("givenNullFullName_WhenCreatePatientEntity_ThenPatientEntityWillThrowsEmptyFullNameException")
    void givenNullFullName_WhenCreatePatientEntity_ThenPatientEntityWillThrowsEmptyFullNameException() {
        assertThrowsExactly(EmptyFullNameException.class, () -> Patient.of(null, phoneNumber));
    }

}
