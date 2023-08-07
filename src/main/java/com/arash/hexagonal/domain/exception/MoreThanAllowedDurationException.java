package com.arash.hexagonal.domain.exception;

import lombok.Getter;

@Getter
public class MoreThanAllowedDurationException extends BusinessDomainException {

    private final long duration;

    public MoreThanAllowedDurationException(long duration) {
        super(duration + " minutes is more than the maximum allowed duration");
        this.duration = duration;
    }
}
