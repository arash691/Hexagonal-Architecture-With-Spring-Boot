package com.blubank.doctorappointment.domain.predicates;

import java.util.function.Predicate;

/**
 * Created by arash on 24.10.22.
 */

public abstract class AbstractPredicate<T> implements Predicate<T> {
    public abstract void check(T t);
}
