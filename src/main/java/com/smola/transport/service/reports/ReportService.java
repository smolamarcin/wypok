package com.smola.transport.service.reports;

import com.smola.transport.model.reports.MonthlyReport;
import com.smola.transport.model.reports.Report;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface ReportService {
    ResponseEntity<Report> getDailyReport(LocalDate startDate, LocalDate endDate);

    ResponseEntity<Report> getSummaryReport();

    ResponseEntity<MonthlyReport> getMonthlyReport();
}
