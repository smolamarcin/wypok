package com.smola.transport.service.reports.logic;

import com.smola.transport.model.common.Transit;

import java.util.List;

public interface ReportCalculator<T, X> {
    T calculate(List<X> x);
}
