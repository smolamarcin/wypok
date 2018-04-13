package com.smola.transport.service;

import com.smola.transport.model.Transit;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TransitService {
    ResponseEntity<Transit> addTransit(Transit transit);
}
