package com.blubank.doctorappointment.domain.predicates;

import com.blubank.doctorappointment.domain.exception.BusinessDomainException;
import com.blubank.doctorappointment.domain.exception.NullMedicalNoException;
import com.blubank.doctorappointment.domain.vo.MedicalNo;

/**
 * Created by arash on 24.10.22.
 */

public class IsNullMedicalNo extends AbstractPredicate<MedicalNo>{

    @Override
    public void check(MedicalNo medicalNo) {
        if(test(medicalNo)){
            throw new NullMedicalNoException("medicalNo is null");
        }
    }

    @Override
    public boolean test(MedicalNo medicalNo) {
        return medicalNo.getMedicalNo() == null;
    }
}
