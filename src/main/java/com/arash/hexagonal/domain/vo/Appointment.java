package com.arash.hexagonal.domain.vo;

import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.entity.Patient;

/**
 * @author iman hosseinzadeh
 */
public record Appointment(Doctor doctor, Patient patient, OpenTime openTime, Integer version) {
}