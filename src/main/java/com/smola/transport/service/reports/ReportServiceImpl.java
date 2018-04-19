package com.smola.transport.service.reports;

import com.smola.transport.model.common.Distance;
import com.smola.transport.model.reports.Report;
import com.smola.transport.model.reports.SummaryReport;
import com.smola.transport.model.common.Transit;
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

    private Report calculateReport(List<Transit> transits) {
        BigDecimal summaryPrice = calculateSummaryPrice(transits);
        Distance summaryDistance = calculateSummaryDistance(transits);

        return new SummaryReport(summaryPrice, summaryDistance);
    }

    private Distance calculateSummaryDistance(List<Transit> transits) {
        long summaryDistance = transits.stream()
                .map(Transit::getDistance)
                .mapToLong(Distance::getMeters).sum();
        return new Distance(summaryDistance);
    }

    private BigDecimal calculateSummaryPrice(List<Transit> transits) {
        return transits.stream()
                .map(Transit::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
