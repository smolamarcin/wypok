package com.smola.transport.model.reports;

import com.smola.transport.model.common.Distance;

import java.math.BigDecimal;

public interface Report {

    BigDecimal getPrice();

    Distance getDistance();
}
