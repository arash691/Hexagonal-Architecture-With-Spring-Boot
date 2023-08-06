package com.arash.hexagonal.domain.vo;

import com.arash.hexagonal.domain.exception.InvalidStartAndEndTimeException;
import com.arash.hexagonal.domain.exception.LessThanAllowedDurationException;
import com.arash.hexagonal.domain.exception.MoreThanAllowedDurationException;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * @author iman hosseinzaeh
 */
public record TimeDuration(LocalTime begin, LocalTime end) {
    private static final short MINIMUM_MIN_DURATION = 30;
    private static final short MAXIMUM_MIN_DURATION = 60;

    public TimeDuration {
        if (end.isBefore(begin))
            throw new InvalidStartAndEndTimeException();

        long duration = begin.until(end, ChronoUnit.MINUTES);
        if (duration < MINIMUM_MIN_DURATION)
            throw new LessThanAllowedDurationException(duration);

        if (duration > MAXIMUM_MIN_DURATION)
            throw new MoreThanAllowedDurationException(duration);
    }

}
