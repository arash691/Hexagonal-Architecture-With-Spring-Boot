package com.arash.hexagonal.domain.vo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author a.ariani
 */
public class VisitDate {
    private final LocalDate visitDate;

    public VisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public static VisitDate of(LocalDate visitDate) {
        return new VisitDate(visitDate);
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitDate visitDate1 = (VisitDate) o;
        return visitDate.equals(visitDate1.visitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitDate);
    }
}
