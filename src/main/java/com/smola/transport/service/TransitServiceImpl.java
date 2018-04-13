package com.smola.transport.service;

import com.smola.transport.model.Transit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransitServiceImpl implements TransitService {

    @Override
    public ResponseEntity<Transit> addTransit(Transit transit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transit);
    }
}
