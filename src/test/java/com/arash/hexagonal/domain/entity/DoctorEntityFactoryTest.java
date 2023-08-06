package com.arash.hexagonal.domain.entity;

import com.arash.hexagonal.domain.exception.EmptyFullNameException;
import com.arash.hexagonal.domain.exception.InvalidStartAndEndTimeException;
import com.arash.hexagonal.domain.exception.LessThanAllowedDurationException;
import com.arash.hexagonal.domain.exception.NullMedicalNumberException;
import com.arash.hexagonal.domain.vo.FullName;
import com.arash.hexagonal.domain.vo.Id;
import com.arash.hexagonal.domain.vo.MedicalNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arash on 21.10.22.
 */

class DoctorEntityFactoryTest extends EntityFactoryTest {

    @Test
    @DisplayName("givenSimpleId_WhenCreateDoctorEntity_ThenDoctorEntityIdIsEqualsWithSimpleId")
    void givenSimpleId_WhenCreateDoctorEntity_ThenDoctorEntityIdIsEqualsWithSimpleId() {
        Doctor doctorWithSimpleId = Doctor.of(id);
        assertEquals(doctorWithSimpleId.getId(), new Id(id));
    }

    @Test
    @DisplayName("givenMedicalNoAndFullName_WhenCreateDoctorEntity_ThenDoctorEntityPropertiesValuesAreExpected")
    void givenMedicalNoAndFullName_WhenCreateDoctorEntity_ThenDoctorEntityPropertiesValuesAreExpected() {
        Doctor doctorWithMedicalNoAndFullName = Doctor.of(id, medicalNo, fullName);
        assertEquals(doctorWithMedicalNoAndFullName.getMedicalNumber().value(), new MedicalNumber(medicalNo).value());
        assertEquals(doctorWithMedicalNoAndFullName.getFullName().value(), new FullName(fullName).value());
    }

    @Test
    @DisplayName("givenNullMedicalNo_WhenCreateDoctorEntity_ThenDoctorEntityWillThrowsNullMedicalNoException")
    void givenNullMedicalNo_WhenCreateDoctorEntity_ThenDoctorEntityWillThrowsNullMedicalNoException() {
        assertThrowsExactly(NullMedicalNumberException.class, () -> Doctor.of(id, null, fullName));
    }

    @Test
    @DisplayName("givenNullFullName_WhenCreateDoctorEntity_ThenDoctorEntityWillThrowsEmptyFullNameException")
    void givenNullFullName_WhenCreateDoctorEntity_ThenDoctorEntityWillThrowsEmptyFullNameException() {
        assertThrowsExactly(EmptyFullNameException.class, () -> Doctor.of(id, medicalNo, null));
    }

    @Test
    @DisplayName("givenInValidOpenTimeWithDurationLessThan30Min_WhenCreateDoctorEntityAndAddOpenTime_ThenOpenTimeDurationValueObjectThrowsLessThanAllowedDurationException")
    void givenInValidOpenTimeWithDurationLessThan30Min_WhenCreateDoctorEntityAndAddOpenTime_ThenOpenTimeDurationValueObjectThrowsLessThanAllowedDurationException() {
        Doctor doctor = Doctor.of(id, medicalNo, fullName);

        assertThrowsExactly(LessThanAllowedDurationException.class, () ->
                doctor.addOpenTime(createInValidDurationOpenTime()));

    }

    @Test
    @DisplayName("givenInValidOpenTimeWithStartTimeLessThanEndTime_WhenCreateDoctorEntityAndAddOpenTime_ThenThrowsInvalidStartAndEndTimeException")
    void givenInValidOpenTimeWithStartTimeLessThanEndTime_WhenCreateDoctorEntityAndAddOpenTime_ThenThrowsInvalidStartAndEndTimeException() {
        assertThrowsExactly(InvalidStartAndEndTimeException.class, () -> {
            Doctor doctor = Doctor.of(id, medicalNo, fullName);
            doctor.addOpenTime(createInvalidStaringPointTime());
        });
    }

    @Test
    @DisplayName("givenValidOpenThanEndTime_WhenCreateDoctorEntityAndAddOpenTime_ThenDurationIsChunkedInto30MinWhichExpected")
    void givenValidOpenThanEndTime_WhenCreateDoctorEntityAndAddOpenTime_ThenDurationIsChunkedInto30MinWhichExpected() {
        Doctor doctor = Doctor.of(id, medicalNo, fullName, new ArrayList<>());
        doctor.addOpenTime(createValidOpenTime());
        assertNotNull(doctor.getAppointments());
        assertEquals(doctor.getAppointments().size(), NumberOfExpectation);
    }


}
