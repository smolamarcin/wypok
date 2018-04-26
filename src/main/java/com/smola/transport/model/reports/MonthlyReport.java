package com.smola.transport.model.reports;

import com.smola.transport.model.common.Distance;
import com.smola.transport.model.common.Transit;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class MonthlyReport implements Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<Report> dailyReports;

    public MonthlyReport(List<Report> dailyReports) {
        this.dailyReports = dailyReports;
    }

    public List<Report> getDailyReports() {
        return dailyReports;
    }

    public void setDailyReports(List<Report> dailyReports) {
        this.dailyReports = dailyReports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public BigDecimal getPrice() {
        return dailyReports.stream()
                .map(Report::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Distance getDistance() {
        return new Distance(
                dailyReports.stream()
                        .map(Report::getDistance)
                        .mapToLong(Distance::getMeters).sum());
    }
}

