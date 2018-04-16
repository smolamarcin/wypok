package com.smola.transport.service;

import com.google.maps.model.Distance;

public interface GoogleApiImpl {
    Distance calculateDistance(String sourceAddress, String destinationAddress);
}
