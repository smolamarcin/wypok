package com.smola.transport.controller;

import com.smola.transport.model.Report;
import com.smola.transport.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/daily")
    ResponseEntity<Report> getDailyReport(@RequestBody LocalDate startDate, LocalDate endDate) {
        return reportService.getDailyReport(startDate, endDate);
    }

    @GetMapping()
    ResponseEntity<Report> getSummaryReport(){
        return reportService.getSummaryReport();
    }

}
