package com.arash.hexagonal.domain.predicates;

import com.arash.hexagonal.domain.exception.EmptyFullNameException;
import com.arash.hexagonal.domain.vo.FullName;

/**
 * Created by arash on 26.10.22.
 */

public class IsNotNullOrEmptyFullName extends AbstractPredicate<FullName> {
    @Override
    public void check(FullName fullName) {
        if (!test(fullName)) {
            throw new EmptyFullNameException("fullName is required");
        }
    }

    @Override
    public boolean test(FullName fullName) {
        return fullName != null && fullName.getFullName() != null && !fullName.getFullName().isBlank();
    }
}
