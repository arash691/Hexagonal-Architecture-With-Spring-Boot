package com.arash.hexagonal.domain.exception;

import lombok.Getter;

@Getter
public class LessThanAllowedDurationException extends BusinessDomainException {

    private final long duration;

    public LessThanAllowedDurationException(long duration) {
        super(duration + " minutes is less than the minimum allowed duration");
        this.duration = duration;
    }
}
