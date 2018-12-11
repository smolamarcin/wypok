package com.smola.transport.service.reports;

import com.smola.transport.model.common.Distance;
import com.smola.transport.model.common.Transit;
import com.smola.transport.model.reports.MonthlyReport;
import com.smola.transport.model.reports.Report;
import com.smola.transport.model.reports.SummaryReport;
import com.smola.transport.repository.TransitRepository;
import com.smola.transport.service.reports.logic.MonthlyReportGenerator;
import com.smola.transport.service.reports.logic.ReportCalculator;
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
    private ReportCalculator<BigDecimal, Transit> summaryPriceCalculator;
    private ReportCalculator<Distance, Transit> summaryDistanceCalculator;
    private MonthlyReportGenerator monthlyReportGenerator;

    @Autowired
    public ReportServiceImpl(TransitRepository transitRepository,
                             ReportCalculator<BigDecimal, Transit> summaryPriceCalculator,
                             ReportCalculator<Distance, Transit> summaryDistanceCalculator,
                             MonthlyReportGenerator monthlyReportGenerator) {
        this.transitRepository = transitRepository;
        this.summaryPriceCalculator = summaryPriceCalculator;
        this.summaryDistanceCalculator = summaryDistanceCalculator;
        this.monthlyReportGenerator = monthlyReportGenerator;
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

    public ResponseEntity<MonthlyReport> getMonthlyReport() {
        return ResponseEntity.status(HttpStatus.OK).body(monthlyReportGenerator.calculate(LocalDate.now()));
    }

    private Report calculateReport(List<Transit> transits) {
        BigDecimal summaryPrice = summaryPriceCalculator.calculate(transits);
        Distance summaryDistance = summaryDistanceCalculator.calculate(transits);

        return new SummaryReport(summaryPrice, summaryDistance);
    }


}
