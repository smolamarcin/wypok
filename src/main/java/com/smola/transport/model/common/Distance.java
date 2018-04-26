package com.smola.transport.model.common;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Distance implements Serializable {
    private long meters = 0;

    public Distance(long meters) {
        this.meters = meters;
    }

    public long getMeters() {
        return meters;
    }

    public Distance() {
    }

    public void setMeters(long meters) {
        this.meters = meters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return meters == distance.meters;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meters);
    }
}
