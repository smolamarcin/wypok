package com.smola.transport.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Meters {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long meters;

    public Meters(long meters) {
        this.meters = meters;
    }

    public long getMeters() {
        return meters;
    }



}
