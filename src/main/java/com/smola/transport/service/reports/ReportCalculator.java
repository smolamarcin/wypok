package com.smola.transport.service.reports;

import com.smola.transport.model.common.Transit;

import java.util.List;

public interface ReportCalculator<T> {
    T calculate(List<Transit> transits);
}
