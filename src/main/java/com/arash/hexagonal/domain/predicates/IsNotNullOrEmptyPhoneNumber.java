package com.arash.hexagonal.domain.predicates;

import com.arash.hexagonal.domain.exception.EmptyPhoneNumberException;
import com.arash.hexagonal.domain.vo.PhoneNumber;

/**
 * Created by arash on 26.10.22.
 */

public class IsNotNullOrEmptyPhoneNumber extends AbstractPredicate<PhoneNumber> {
    @Override
    public void check(PhoneNumber phoneNumber) {
        if (!test(phoneNumber)) {
            throw new EmptyPhoneNumberException("phoneNumber is required");
        }
    }

    @Override
    public boolean test(PhoneNumber phoneNumber) {
        return phoneNumber != null && phoneNumber.getPhoneNumber() != null && !phoneNumber.getPhoneNumber().isBlank();
    }
}
