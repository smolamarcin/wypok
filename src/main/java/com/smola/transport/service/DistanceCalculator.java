package com.smola.transport.service;


import com.smola.transport.model.Meters;

import java.util.Optional;

public interface DistanceCalculator {
    Optional<Meters> calculate(String sourceAddress, String destinationAddress);

}
