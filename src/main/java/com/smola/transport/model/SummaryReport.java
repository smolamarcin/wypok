package com.smola.transport.model;


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

    Distance distance;

    BigDecimal price;

    public SummaryReport(BigDecimal price) {
        this.price = price;
    }

    public SummaryReport(BigDecimal price, Distance distance) {
        this.price = price;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Distance getDistance() {
        return distance;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }
}
