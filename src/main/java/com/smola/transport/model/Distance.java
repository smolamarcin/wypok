package com.smola.transport.model;

import javax.persistence.*;

@Embeddable
public class Distance {
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
}
