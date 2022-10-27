package com.blubank.doctorappointment.infrastructure.input.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author a.ariani
 */
public class CreateOpenTimeRequest {
    @JsonDeserialize(using = LocalDateDeserializer.class)
   @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "visit date is required")
    private LocalDate visitDate;
    @NotNull(message = "startTime is required")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime startTime;
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @NotNull(message = "endTime is required")
    @JsonFormat(pattern="HH:mm")
    private LocalTime endTime;

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
