package com.smola.transport.service.reports;

import com.smola.transport.model.common.Distance;
import com.smola.transport.model.reports.Report;
import com.smola.transport.model.reports.SummaryReport;
import com.smola.transport.model.common.Transit;
import com.smola.transport.repository.TransitRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    private TransitRepository transitRepository;
    @Resource(name = "summaryPriceCalculator")
    private ReportCalculator<BigDecimal, Transit> summaryPriceCalculator;
    @Resource(name = "summaryDistanceCalculator")
    private ReportCalculator<Distance, Transit> summaryDistanceCalculator;

    @Autowired
    public ReportServiceImpl(TransitRepository transitRepository) {
        this.transitRepository = transitRepository;
    }

    public ResponseEntity<Report> getDailyReport(LocalDate startDate, LocalDate endDate) {
        List<Transit> transits = transitRepository.findByDateBetween(startDate, endDate);
        Report dailyReport = calculateReport(transits);
        return ResponseEntity.status(HttpStatus.OK).body(dailyReport);
    }

    public ResponseEntity<Report> getSummaryReport() {
        List<Transit> transits = transitRepository.findAll();
        Report summaryReport = calculateReport(transits);
        return ResponseEntity.status(HttpStatus.OK).body(summaryReport);
    }

    @Override
    public ResponseEntity<List<Report>> getMonthlyReport() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        List<Transit> byDateBetween = transitRepository.findByDateBetween(firstDayOfMonth, today);
        Map<LocalDate, List<Report>> myMap = calculateDailyReports(byDateBetween);
        return null;
    }

    private Map<LocalDate, List<Report>> calculateDailyReports(List<Transit> byDateBetween) {
        //TODO::
        return null;
    }

    private Report calculateReport(List<Transit> transits) {
        BigDecimal summaryPrice = summaryPriceCalculator.calculate(transits);
        Distance summaryDistance = summaryDistanceCalculator.calculate(transits);

        return new SummaryReport(summaryPrice, summaryDistance);
    }


}
