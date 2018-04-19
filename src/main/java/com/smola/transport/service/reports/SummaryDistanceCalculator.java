package com.smola.transport.service.reports;

import com.smola.transport.model.common.Distance;
import com.smola.transport.model.common.Transit;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component("summaryDistanceCalculator")
public class SummaryDistanceCalculator implements ReportCalculator<Distance> {
    public Distance calculate(List<Transit> transits) {
        long summaryDistance = transits.stream()
                .map(Transit::getDistance)
                .mapToLong(Distance::getMeters).sum();
        return new Distance(summaryDistance);
    }
}
