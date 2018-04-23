package com.smola.transport.model.reports;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class MonthlyReport implements Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Set<DailyReport> dailyReports = new HashSet<>();


    public Set<DailyReport> getDailyReports() {
        return dailyReports;
    }

    public void addDailyReport(DailyReport report) {
        dailyReports.add(report);
    }
}

