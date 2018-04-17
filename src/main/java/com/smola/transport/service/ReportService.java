package com.smola.transport.service;

import com.smola.transport.model.Report;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface ReportService {
    ResponseEntity<Report> getDailyReport(LocalDate startDate, LocalDate endDate);

    ResponseEntity<Report> getSummaryReport();
}
