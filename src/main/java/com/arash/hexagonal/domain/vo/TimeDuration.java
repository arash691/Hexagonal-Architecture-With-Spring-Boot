package com.arash.hexagonal.domain.vo;

import java.time.LocalTime;
import java.util.Objects;

/**
 * @author a.ariani
 */
public class TimeDuration {
    private final LocalTime start;
    private final LocalTime end;

    public TimeDuration(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public static TimeDuration of(LocalTime start, LocalTime end) {
        return new TimeDuration(start, end);
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeDuration that = (TimeDuration) o;
        return start.equals(that.start) &&
                end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "TimeDuration{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
