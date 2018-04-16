package com.smola.transport.service;

import com.smola.transport.model.Transit;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransitService {
    ResponseEntity<Transit> addTransit(Transit transit);

    ResponseEntity<List<Transit>> getTransits();
}
