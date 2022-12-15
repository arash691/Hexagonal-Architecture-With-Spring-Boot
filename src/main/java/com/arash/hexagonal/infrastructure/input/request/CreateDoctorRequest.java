package com.arash.hexagonal.infrastructure.input.request;

import com.arash.hexagonal.domain.entity.Doctor;

/**
 * @author a.ariani
 */
public class CreateDoctorRequest {
    private String fullName;
    private Long medicalNo;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getMedicalNo() {
        return medicalNo;
    }

    public void setMedicalNo(Long medicalNo) {
        this.medicalNo = medicalNo;
    }

    public Doctor toDomain() {
        return Doctor.of(null, medicalNo, fullName);
    }
}
