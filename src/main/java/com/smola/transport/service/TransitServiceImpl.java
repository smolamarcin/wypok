package com.smola.transport.service;

import com.smola.transport.model.Transit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransitServiceImpl implements TransitService {

    @Override
    public ResponseEntity<Transit> addTransit() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
