package com.smola.transport.model;

import com.google.maps.model.Distance;

import java.math.BigDecimal;

public interface Report {
    Distance getDistance();

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    void setDistance(Distance distance);
}
