package com.arash.hexagonal.domain.vo;

import com.arash.hexagonal.domain.entity.Doctor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * @author a.ariani
 */
public class OpenTime {

    private final VisitDate visitDate;
    private final TimeDuration timeDuration;


    public OpenTime(VisitDate visitDate, TimeDuration timeDuration, Doctor doctor) {
        this.visitDate = visitDate;
        this.timeDuration = timeDuration;

    }

    public static OpenTime of(LocalDate visitDate, LocalTime start, LocalTime end) {
        return new OpenTime(VisitDate.of(visitDate), TimeDuration.of(start, end), null);
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public TimeDuration getTimeDuration() {
        return timeDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenTime openTime = (OpenTime) o;
        return visitDate.equals(openTime.visitDate) &&
                timeDuration.equals(openTime.timeDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitDate, timeDuration);
    }

    @Override
    public String toString() {
        return "OpenTime{" +
                "visitDate=" + visitDate +
                ", timeDuration=" + timeDuration +
                '}';
    }
}
