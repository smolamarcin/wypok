package com.smola.transport.service.transits;

import com.smola.transport.model.common.Transit;
import com.smola.transport.repository.TransitRepository;
import com.smola.transport.service.distance.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TransitServiceImpl implements TransitService {
    private TransitRepository transitRepository;
    private DistanceCalculator distanceCalculator;

    @Autowired
    public TransitServiceImpl(TransitRepository transitRepository, DistanceCalculator distanceCalculator) {
        this.transitRepository = transitRepository;
        this.distanceCalculator = distanceCalculator;
    }

    @Override
    public ResponseEntity<Transit> addTransit(Transit transit) {
        return distanceCalculator.calculate(transit.getSourceAddress(), transit.getDestinationAddress())
                .map(e -> {
                    transit.setDistance(e);
                    return ResponseEntity.status(HttpStatus.CREATED).body(transitRepository.save(transit));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @Override
    public ResponseEntity<List<Transit>> getTransits() {
        return ResponseEntity.status(HttpStatus.OK).body(transitRepository.findAll());
    }
}
