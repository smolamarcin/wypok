package com.smola.transport.service.reports;

import com.smola.transport.model.reports.Report;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    ResponseEntity<Report> getDailyReport(LocalDate startDate, LocalDate endDate);

    ResponseEntity<Report> getSummaryRepottrt();

    ResponseEntity<List<Report>> getMonthlyReport();
}
