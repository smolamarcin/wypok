package com.smola.transport.service;

import com.google.maps.model.Distance;
import com.smola.transport.model.Transit;
import com.smola.transport.repository.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class TransitServiceImpl implements TransitService {
    private TransitRepository transitRepository;
    private DistanceCalculatorImpl distanceCalculator;

    @Autowired
    public TransitServiceImpl(TransitRepository transitRepository, DistanceCalculatorImpl distanceCalculator) {
        this.transitRepository = transitRepository;
        this.distanceCalculator = distanceCalculator;
    }

    @Override
    public ResponseEntity<Transit> addTransit(Transit transit) {
        ResponseEntity<Transit> responseEntity;
        Optional<Distance> distance = distanceCalculator.calculate(transit.getSourceAddress(), transit.getDestinationAddress());
        if (distance.isPresent()){
            transit.setDistance(distance.get());
            responseEntity =  ResponseEntity.status(HttpStatus.CREATED).body(transitRepository.save(transit));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<List<Transit>> getTransits() {
        return ResponseEntity.status(HttpStatus.OK).body(transitRepository.findAll());
    }
}
