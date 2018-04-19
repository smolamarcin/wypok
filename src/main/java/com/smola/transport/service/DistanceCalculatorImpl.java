package com.smola.transport.service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import com.smola.transport.model.Meters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
class DistanceCalculatorImpl implements DistanceCalculator {
    @Value("${google.api.key}")
    private String apiToken;

    public Optional<Meters> calculate(String sourceAddress, String destinationAddress) {
        Optional<Distance> googleDistance = calculateGoogleDistance(sourceAddress, destinationAddress);
        if (googleDistance.isPresent()){
            Meters meters = new Meters(googleDistance.get().inMeters);
            return Optional.of(meters);
        }
        return Optional.empty();
    }

    private Optional<Distance> calculateGoogleDistance(String sourceAddress, String destinationAddress) {
        Optional<Distance> distance = Optional.empty();
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiToken)
                .build();
        DistanceMatrixApiRequest distanceMatrixApiRequest = DistanceMatrixApi.newRequest(context);
        DistanceMatrix matrix;
        try {
            matrix = distanceMatrixApiRequest
                    .mode(TravelMode.DRIVING)
                    .units(Unit.METRIC)
                    .origins(sourceAddress)
                    .destinations(destinationAddress)
                    .await();
            if (matrix.rows[0].elements[0].distance == null) {
                distance = Optional.empty();
            } else {
                distance = Optional.of(matrix.rows[0].elements[0].distance);
            }
        } catch (ApiException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return distance;
    }
}
