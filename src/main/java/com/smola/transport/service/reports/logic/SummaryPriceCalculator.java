package com.smola.transport.service.reports.logic;

import com.smola.transport.model.common.Transit;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component("summaryPriceCalculator")
public class SummaryPriceCalculator implements ReportCalculator<BigDecimal, Transit> {
    public BigDecimal calculate(List<Transit> transits) {
        return transits.stream()
                .map(Transit::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
