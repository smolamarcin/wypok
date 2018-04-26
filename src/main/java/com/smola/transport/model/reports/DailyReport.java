package com.smola.transport.model.reports;


import com.smola.transport.model.common.Distance;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class DailyReport implements Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Distance distance;

    private LocalDate date;

    private BigDecimal price;
    @ManyToOne
    @JoinColumn
    private MonthlyReport monthlyReport;

    public DailyReport(LocalDate date, DailyStatistics dailyStatistics) {
        this.date = date;
        this.distance = dailyStatistics.getSummaryDistance();
        this.price = dailyStatistics.getSummaryPrice();
    }

    public DailyReport(LocalDate date) {
        this.date = date;
    }

    public Distance getDistance() {
        return distance;
    }

    public BigDecimal getPrice() {
        return price;
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
