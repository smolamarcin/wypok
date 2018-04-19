package com.smola.transport.service.reports;

import com.smola.transport.model.common.Transit;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SummaryPriceCalculator implements ReportCalculator<BigDecimal> {
    public BigDecimal calculate(List<Transit> transits) {
        return transits.stream()
                .map(Transit::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
