package com.smola.transport.model.common;

import javax.persistence.*;
import java.io.Serializable;

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
}
