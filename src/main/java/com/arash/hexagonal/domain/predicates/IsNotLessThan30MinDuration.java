package com.arash.hexagonal.domain.predicates;


import com.arash.hexagonal.domain.vo.OpenTime;

import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

/**
 * Created by arash on 24.10.22.
 */

public class IsNotLessThan30MinDuration implements Predicate<OpenTime> {
    private final static Long MIN_DURATION = 30L;

    @Override
    public boolean test(OpenTime openTime) {
        return openTime.getTimeDuration().getStart().until(openTime.getTimeDuration().getEnd(), ChronoUnit.MINUTES) >= MIN_DURATION;
    }
}
