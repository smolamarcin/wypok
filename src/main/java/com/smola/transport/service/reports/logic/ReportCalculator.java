package com.smola.transport.service.reports.logic;

import java.util.List;

public interface ReportCalculator<T, X> {
    T calculate(List<X> x);
}
