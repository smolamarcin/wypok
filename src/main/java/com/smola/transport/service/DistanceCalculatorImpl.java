package com.smola.transport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.Distance;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class DistanceCalculatorImpl {
    @Autowired
    private GoogleApiImpl googleApi;
    @Value("${google.api.key}")
    private String apiToken;

    public Distance calculate(String sourceAddress, String destinationAddress) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiToken)
                .build();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return new Distance();
    }
}
