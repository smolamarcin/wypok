package com.smola.transport.controller;

import com.smola.transport.model.common.DateWrapper;
import com.smola.transport.model.reports.Report;
import com.smola.transport.service.reports.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/daily")
    ResponseEntity<Report> getDailyReport(@RequestBody DateWrapper dateWrapper) {
        return reportService.getDailyReport(dateWrapper.getStartDate(), dateWrapper.getEndDate());
    }

    @GetMapping()
    ResponseEntity<Report> getSummaryReport() {
        return reportService.getSummaryReport();
    }

    @GetMapping("/monthly")
    ResponseEntity<List<Report>> getMonthlyReport() {
        return reportService.getMonthlyReport();
    }

}
