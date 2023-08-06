package com.arash.hexagonal.domain.entity;

import com.arash.hexagonal.domain.exception.EmptyFullNameException;
import com.arash.hexagonal.domain.exception.InvalidStartAndEndTimeException;
import com.arash.hexagonal.domain.exception.NullMedicalNoException;
import com.arash.hexagonal.domain.vo.FullName;
import com.arash.hexagonal.domain.vo.ID;
import com.arash.hexagonal.domain.vo.MedicalNo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arash on 21.10.22.
 */

public class DoctorEntityFactoryTest extends EntityFactoryTest {

    @Test
    @DisplayName("givenSimpleId_WhenCreateDoctorEntity_ThenDoctorEntityIdIsEqualsWithSimpleId")
    public void givenSimpleId_WhenCreateDoctorEntity_ThenDoctorEntityIdIsEqualsWithSimpleId() {
        Doctor doctorWithSimpleId = Doctor.of(id);
        assertEquals(doctorWithSimpleId.getId(), ID.of(id));
    }

    @Test
    @DisplayName("givenMedicalNoAndFullName_WhenCreateDoctorEntity_ThenDoctorEntityPropertiesValuesAreExpected")
    public void givenMedicalNoAndFullName_WhenCreateDoctorEntity_ThenDoctorEntityPropertiesValuesAreExpected() {
        Doctor doctorWithMedicalNoAndFullName = Doctor.of(id, medicalNo, fullName);
        assertEquals(doctorWithMedicalNoAndFullName.getMedicalNo().getMedicalNo(), MedicalNo.of(medicalNo).getMedicalNo());
        assertEquals(doctorWithMedicalNoAndFullName.getFullName().value(), new FullName(fullName).value());
    }

    @Test
    @DisplayName("givenNullMedicalNo_WhenCreateDoctorEntity_ThenDoctorEntityWillThrowsNullMedicalNoException")
    public void givenNullMedicalNo_WhenCreateDoctorEntity_ThenDoctorEntityWillThrowsNullMedicalNoException() {
        assertThrowsExactly(NullMedicalNoException.class, () -> Doctor.of(id, null, fullName));
    }

    @Test
    @DisplayName("givenNullFullName_WhenCreateDoctorEntity_ThenDoctorEntityWillThrowsEmptyFullNameException")
    public void givenNullFullName_WhenCreateDoctorEntity_ThenDoctorEntityWillThrowsEmptyFullNameException() {
        assertThrowsExactly(EmptyFullNameException.class, () -> Doctor.of(id, medicalNo, null));
    }

    @Test
    @DisplayName("givenInValidOpenTimeWithDurationLessThan30Min_WhenCreateDoctorEntityAndAddOpenTime_ThenOpenTimeIsEmpty")
    public void givenInValidOpenTimeWithDurationLessThan30Min_WhenCreateDoctorEntityAndAddOpenTime_ThenOpenTimeIsEmpty() {
        Doctor doctor = Doctor.of(id, medicalNo, fullName);
        doctor.addOpenTime(invalidDuration);
        assertNull(doctor.getAppointments());
    }

    @Test
    @DisplayName("givenInValidOpenTimeWithStartTimeLessThanEndTime_WhenCreateDoctorEntityAndAddOpenTime_ThenThrowsInvalidStartAndEndTimeException")
    public void givenInValidOpenTimeWithStartTimeLessThanEndTime_WhenCreateDoctorEntityAndAddOpenTime_ThenThrowsInvalidStartAndEndTimeException() {
        assertThrowsExactly(InvalidStartAndEndTimeException.class, () -> {
            Doctor doctor = Doctor.of(id, medicalNo, fullName);
            doctor.addOpenTime(invalidStartingPointTime);
        });
    }

    @Test
    @DisplayName("givenValidOpenThanEndTime_WhenCreateDoctorEntityAndAddOpenTime_ThenDurationIsChunkedInto30MinWhichExpected")
    public void givenValidOpenThanEndTime_WhenCreateDoctorEntityAndAddOpenTime_ThenDurationIsChunkedInto30MinWhichExpected() {
        Doctor doctor = Doctor.of(id, medicalNo, fullName, new ArrayList<>());
        doctor.addOpenTime(validOpenTime);
        assertNotNull(doctor.getAppointments());
        assertEquals(doctor.getAppointments().size(), NumberOfExpectation);
    }


}
