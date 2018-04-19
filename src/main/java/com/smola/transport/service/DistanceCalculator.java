package com.smola.transport.service;


import com.smola.transport.model.Distance;

import java.util.Optional;

public interface DistanceCalculator {
    Optional<Distance> calculate(String sourceAddress, String destinationAddress);

}
