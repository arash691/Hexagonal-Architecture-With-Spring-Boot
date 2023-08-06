package com.arash.hexagonal.domain.entity;

import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.OpenTime;
import com.arash.hexagonal.domain.vo.TimeDuration;
import com.arash.hexagonal.domain.vo.VisitDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by arash on 21.10.22.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class EntityFactoryTest {

    protected static Long id;
    protected static Long medicalNo;
    protected static String fullName;
    protected static String phoneNumber;
    protected static OpenTime validOpenTime;
    protected static OpenTime invalidDuration;
    protected static OpenTime invalidStartingPointTime;
    protected static int NumberOfExpectation = 4;
    protected static List<Appointment> expectedAppointments;

    @BeforeAll
    public static void init() {
        id = 100L;
        medicalNo = 123456789L;
        fullName = "arash ariani";
        phoneNumber = "09123456789";
        validOpenTime = createValidOpenTime();
        invalidDuration = createInValidDurationOpenTime();
        invalidStartingPointTime = createInvalidStaringPointTime();
        expectedAppointments = getExpectedAppointment();

    }

    private static OpenTime createInvalidStaringPointTime() {
        return new OpenTime(new VisitDate(LocalDate.of(2022, 1, 1)), new TimeDuration(LocalTime.of(10, 0), LocalTime.of(9, 30)));
    }

    public static OpenTime createValidOpenTime() {
        return new OpenTime(new VisitDate(LocalDate.of(2022, 1, 1)), new TimeDuration(LocalTime.of(9, 0), LocalTime.of(11, 0)));
    }

    public static OpenTime createInValidDurationOpenTime() {
        return new OpenTime(new VisitDate(LocalDate.of(2022, 1, 1)), new TimeDuration(LocalTime.of(9, 0), LocalTime.of(9, 15)));
    }

    public static List<Appointment> getExpectedAppointment() {
        return List.of(new Appointment(Doctor.of(id, medicalNo, fullName), null,
                        new OpenTime(new VisitDate(validOpenTime.visitDate().value()),
                                new TimeDuration(LocalTime.of(9, 0), LocalTime.of(9, 30))), 0),
                new Appointment(Doctor.of(id, medicalNo, fullName), null,
                        new OpenTime(new VisitDate(validOpenTime.visitDate().value()),
                                new TimeDuration(LocalTime.of(9, 30), LocalTime.of(10, 0))), 0),
                new Appointment(Doctor.of(id, medicalNo, fullName), null,
                        new OpenTime(new VisitDate(validOpenTime.visitDate().value()),
                                new TimeDuration(LocalTime.of(10, 0), LocalTime.of(10, 30))), 0),
                new Appointment(Doctor.of(id, medicalNo, fullName), null,
                        new OpenTime(new VisitDate(validOpenTime.visitDate().value()),
                                new TimeDuration(LocalTime.of(10, 30), LocalTime.of(11, 0))), 0));
    }

}
