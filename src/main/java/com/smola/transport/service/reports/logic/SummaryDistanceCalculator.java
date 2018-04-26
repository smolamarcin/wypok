package com.smola.transport.service.reports.logic;

import com.smola.transport.model.common.Distance;
import com.smola.transport.model.common.Transit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SummaryDistanceCalculator implements ReportCalculator<Distance,Transit> {
    public Distance calculate(List<Transit> transits) {
        long summaryDistance = transits.stream()
                .map(Transit::getDistance)
                .mapToLong(Distance::getMeters).sum();
        return new Distance(summaryDistance);
    }
}
