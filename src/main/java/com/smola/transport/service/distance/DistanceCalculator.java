package com.smola.transport.service.distance;


import com.smola.transport.model.common.Distance;

import java.util.Optional;

public interface DistanceCalculator {
    Optional<Distance> calculate(String sourceAddress, String destinationAddress);

}
