package com.smola.transport.model.reports;


import com.smola.transport.model.common.Distance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class DailyReport implements Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    Distance distance;

    BigDecimal price;

    private LocalDate date;

    public DailyReport(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
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

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyReport that = (DailyReport) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(date);
    }
}
