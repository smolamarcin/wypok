package com.smola.transport.service.reports.logic;

import com.smola.transport.model.common.Distance;
import com.smola.transport.model.common.Transit;
import com.smola.transport.model.reports.DailyReport;
import com.smola.transport.model.reports.MonthlyReport;
import com.smola.transport.repository.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Map<LocalDate, List<Transit>> transitsMap = transitsByDateBetween.stream().collect(Collectors.groupingBy(Transit::getDate));
        transitsMap.entrySet().stream()
        transitsMap.values().stream().flatMap(e -> e.stream().map(Transit::getPrice));

//        Set<Map.Entry<LocalDate, List<Transit>>> entries = transitsMap.entrySet();
//        List<BigDecimal> collect = entries.stream().transitsMap(e -> e.getValue().stream().transitsMap(Transit::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)).collect(Collectors.toList());


        return new MonthlyReport();
    }
}
