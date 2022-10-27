package com.blubank.doctorappointment.infrastructure.input.request;

import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.vo.TimeDuration;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arash on 26.10.22.
 */

public class RemoveOpenTime {
    private LocalDate visitDate;
    private List<TimeDuration> timeDurations;

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public List<TimeDuration> getTimeDurations() {
        return timeDurations;
    }

    public void setTimeDurations(List<TimeDuration> timeDurations) {
        this.timeDurations = timeDurations;
    }

    public List<OpenTime> toDomain() {
        return timeDurations.stream().map(timeDuration -> OpenTime.of(visitDate, timeDuration.getStart()
                , timeDuration.getEnd())).collect(Collectors.toList());
    }
}
