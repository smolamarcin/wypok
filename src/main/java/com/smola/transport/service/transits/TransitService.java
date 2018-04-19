package com.smola.transport.service.transits;

import com.smola.transport.model.common.Transit;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransitService {
    ResponseEntity<Transit> addTransit(Transit transit);

    ResponseEntity<List<Transit>> getTransits();
}
