package com.smola.transport.model.reports;


import com.smola.transport.model.common.Distance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class SummaryReport implements Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Distance distance;

    private BigDecimal price;

    public SummaryReport(BigDecimal price, Distance distance) {
        this.price = price;
        this.distance = distance;
    }

    public Distance getDistance() {
        return distance;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
