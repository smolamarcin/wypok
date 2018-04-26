package com.smola.transport.model.common;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Distance implements Serializable {
    private long meters = 0;

    public Distance() {
    }

    public Distance(long meters) {
        this.meters = meters;
    }

    public long getMeters() {
        return meters;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return meters == distance.meters;
    }

    public int hashCode() {
        return Objects.hash(meters);
    }
}
