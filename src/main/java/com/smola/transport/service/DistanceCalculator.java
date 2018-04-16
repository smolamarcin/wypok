package com.smola.transport.service;


import com.google.maps.model.Distance;

public interface DistanceCalculator {
    Distance calculate(String sourceAddress, String destinationAddress);

}
