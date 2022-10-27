package com.blubank.doctorappointment.domain.vo;

/**
 * @author a.ariani
 */
public class MedicalNo {
    private final Long medicalNo;

    private MedicalNo(Long medicalNo) {
        this.medicalNo = medicalNo;
    }

    public static MedicalNo of(Long medicalNo) {
        return new MedicalNo(medicalNo);
    }

    public Long getMedicalNo() {
        return medicalNo;
    }
}
