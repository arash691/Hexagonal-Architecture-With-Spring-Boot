package com.blubank.doctorappointment.domain.vo;

import java.util.Objects;

/**
 * @author a.ariani
 */
public class ID {
    private final Long id;

    private ID(Long id) {
        this.id = id;
    }

    public static ID of(Long id) {
        return new ID(id);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ID id1 = (ID) o;
        return id.equals(id1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
