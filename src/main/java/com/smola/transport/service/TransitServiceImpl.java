package com.smola.transport.service;

import com.google.maps.model.Distance;
import com.smola.transport.model.Transit;
import com.smola.transport.repository.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransitServiceImpl implements TransitService {

    @Autowired
    private TransitRepository transitRepository;

    @Autowired
    private DistanceCalculatorImpl distanceCalculator;

    @Override
    public ResponseEntity<Transit> addTransit(Transit transit) {
        Distance distance = distanceCalculator.calculate(transit.getSourceAddress(), transit.getDestinationAddress());
        transit.setDistance(distance);
        return ResponseEntity.status(HttpStatus.CREATED).body(transitRepository.save(transit));
    }

    @Override
    public ResponseEntity<List<Transit>> getTransits() {
        return ResponseEntity.status(HttpStatus.OK).body(transitRepository.findAll());
    }
}
