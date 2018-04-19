package com.smola.transport.service.distance;

import com.smola.transport.model.common.Distance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class DistanceCalculatorImpl implements DistanceCalculator {

    private GoogleApiImpl googleApiImpl;

    @Autowired
    public DistanceCalculatorImpl(GoogleApiImpl googleApiImpl) {
        this.googleApiImpl = googleApiImpl;
    }

    public Optional<Distance> calculate(String sourceAddress, String destinationAddress) {
        Optional<com.google.maps.model.Distance> googleDistance = googleApiImpl.calculateGoogleDistance(sourceAddress, destinationAddress);
        if (googleDistance.isPresent()){
            Distance distance = new Distance(googleDistance.get().inMeters);
            return Optional.of(distance);
        }
        return Optional.empty();
    }


}
