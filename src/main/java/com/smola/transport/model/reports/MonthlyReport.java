package com.smola.transport.model.reports;

import javax.persistence.*;
import java.util.List;

@Entity
public class MonthlyReport implements Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<DailyReport> dailyReports;

    public MonthlyReport(List<DailyReport> dailyReports) {
        this.dailyReports = dailyReports;
    }

    public List<DailyReport> getDailyReports() {
        return dailyReports;
    }

    public void setDailyReports(List<DailyReport> dailyReports) {
        this.dailyReports = dailyReports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}

