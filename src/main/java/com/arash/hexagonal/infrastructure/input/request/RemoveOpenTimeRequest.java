package com.arash.hexagonal.infrastructure.input.request;

import com.arash.hexagonal.domain.vo.OpenTime;
import com.arash.hexagonal.domain.vo.TimeDuration;
import com.arash.hexagonal.domain.vo.VisitDate;

import java.time.LocalDate;

/**
 * Created by arash on 26.10.22.
 */

public class RemoveOpenTimeRequest {
    private LocalDate visitDate;
    private TimeDuration timeDurations;

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public TimeDuration getTimeDurations() {
        return timeDurations;
    }

    public void setTimeDurations(TimeDuration timeDurations) {
        this.timeDurations = timeDurations;
    }

    public OpenTime toDomain() {
        return new OpenTime(new VisitDate(visitDate), new TimeDuration(timeDurations.begin(), timeDurations.end()));
    }
}
