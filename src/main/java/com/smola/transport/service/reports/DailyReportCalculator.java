package com.smola.transport.service.reports;

import com.smola.transport.model.common.Transit;
import com.smola.transport.model.reports.Report;

import java.util.List;

public class DailyReportCalculator implements ReportCalculator<Report, Transit> {
    @Override
    public Report calculate(List<Transit> transits) {
        return null;
    }
}
