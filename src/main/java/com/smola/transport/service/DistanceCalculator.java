package com.smola.transport.service;


import com.google.maps.model.Distance;

import java.util.Optional;

public interface DistanceCalculator {
    Optional<Distance> calculate(String sourceAddress, String destinationAddress);

}
