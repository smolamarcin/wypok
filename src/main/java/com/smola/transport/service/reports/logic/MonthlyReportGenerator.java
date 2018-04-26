package com.smola.transport.service.reports.logic;

import com.smola.transport.model.reports.DailyStatistics;
import com.smola.transport.model.common.Distance;
import com.smola.transport.model.common.Transit;
import com.smola.transport.model.reports.DailyReport;
import com.smola.transport.model.reports.MonthlyReport;
import com.smola.transport.model.reports.Report;
import com.smola.transport.repository.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
class MonthlyReportGenerator {
    private TransitRepository transitRepository;
    private ReportCalculator reportCalculator;
    @Resource(name = "summaryPriceCalculator")
    private ReportCalculator<BigDecimal, Transit> summaryPriceCalculator;
    @Resource(name = "summaryDistanceCalculator")
    private ReportCalculator<Distance, Transit> summaryDistanceCalculator;

    @Autowired
    MonthlyReportGenerator(TransitRepository transitRepository) {
        this.transitRepository = transitRepository;
    }

    MonthlyReport calculate(LocalDate date) {
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);

        List<Transit> transitsByDateBetween = transitRepository.findByDateBetween(firstDayOfMonth, date);
        Map<LocalDate, List<Transit>> transitsMap = transitsByDateBetween
                .stream()
                .collect(Collectors.groupingBy(Transit::getDate));

        List<Report> dailyReports = transitsMap
                .entrySet()
                .stream()
                .map(e -> computeDailyStatistics(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        return new MonthlyReport();
    }

    private DailyReport computeDailyStatistics(LocalDate date, List<Transit> transits) {
        return new DailyReport(date, new DailyStatistics(
                new Distance(
                        transits.stream()
                                .map(Transit::getDistance)
                                .mapToLong(Distance::getMeters).sum()),
                transits.stream()
                        .map(Transit::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        ));
    }
}