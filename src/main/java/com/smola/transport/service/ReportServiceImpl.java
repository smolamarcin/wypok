package com.smola.transport.service;

import com.smola.transport.model.Report;
import com.smola.transport.model.SummaryReport;
import com.smola.transport.model.Transit;
import com.smola.transport.repository.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private TransitRepository transitRepository;

    @Autowired
    public ReportServiceImpl(TransitRepository transitRepository) {
        this.transitRepository = transitRepository;
    }

    @Override
    public ResponseEntity<Report> getDailyReport(LocalDate startDate, LocalDate endDate) {
        List<Transit> transits = transitRepository.findByDateBetween(startDate, endDate);
        Report dailyReport = calculateReport(transits);
        return ResponseEntity.status(HttpStatus.OK).body(dailyReport);
    }

    @Override
    public ResponseEntity<Report> getSummaryReport() {
        List<Transit> transits = transitRepository.findAll();
        Report summaryReport = calculateReport(transits);
        return ResponseEntity.status(HttpStatus.OK).body(summaryReport);
    }

    //TODO: add logic here!!!
    private Report calculateReport(List<Transit> transits) {
        BigDecimal summaryPrice = transits.stream()
                .map(Transit::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new SummaryReport(summaryPrice);
    }

}
