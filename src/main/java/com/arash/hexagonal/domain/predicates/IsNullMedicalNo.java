package com.arash.hexagonal.domain.predicates;

import com.arash.hexagonal.domain.exception.NullMedicalNoException;
import com.arash.hexagonal.domain.vo.MedicalNo;

/**
 * Created by arash on 24.10.22.
 */

public class IsNullMedicalNo extends AbstractPredicate<MedicalNo> {

    @Override
    public void check(MedicalNo medicalNo) {
        if (!test(medicalNo)) {
            throw new NullMedicalNoException("medicalNo is null");
        }
    }

    @Override
    public boolean test(MedicalNo medicalNo) {
        return medicalNo != null && medicalNo.getMedicalNo() != null;
    }
}
