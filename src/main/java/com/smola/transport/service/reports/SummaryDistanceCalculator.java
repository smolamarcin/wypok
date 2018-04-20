package com.smola.transport.service.reports;

import com.smola.transport.model.common.Distance;
import com.smola.transport.model.common.Transit;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("summaryDistanceCalculator")
public class SummaryDistanceCalculator implements ReportCalculator<Distance,Transit> {
    public Distance calculate(List<Transit> transits) {
        long summaryDistance = transits.stream()
                .map(Transit::getDistance)
                .mapToLong(Distance::getMeters).sum();
        return new Distance(summaryDistance);
    }
}
