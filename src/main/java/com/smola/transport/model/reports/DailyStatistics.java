package com.smola.transport.model.reports;

import com.smola.transport.model.common.Distance;

import java.math.BigDecimal;

public class DailyStatistics {
    private final Distance summaryDistance;
    private final BigDecimal summaryPrice;

    public DailyStatistics(Distance summaryDistance, BigDecimal summaryPrice) {
        this.summaryDistance = summaryDistance;
        this.summaryPrice = summaryPrice;
    }

    public Distance getSummaryDistance() {
        return summaryDistance;
    }

    public BigDecimal getSummaryPrice() {
        return summaryPrice;
    }

}
