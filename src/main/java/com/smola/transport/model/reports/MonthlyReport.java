package com.smola.transport.model.reports;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class MonthlyReport implements Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private Set<DailyReport> dailyReports = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDailyReports(Set<DailyReport> dailyReports) {
        this.dailyReports = dailyReports;
    }

    public Set<DailyReport> getDailyReports() {
        return dailyReports;
    }

    public void addDailyReport(DailyReport report) {
        dailyReports.add(report);
    }
}

