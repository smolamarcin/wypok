package com.smola.transport.controller;

import com.smola.transport.model.Transit;
import com.smola.transport.service.TransitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transits")
public class TransitController {
    @Autowired
    private TransitService transitService;

    @PostMapping()
    ResponseEntity<Transit> addTransit() {
        return transitService.addTransit();
    }
}
