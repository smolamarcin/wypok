package com.smola.transport.controller;

import com.smola.transport.model.Transit;
import com.smola.transport.service.TransitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transits")
public class TransitController {
    private TransitService transitService;

    @Autowired
    public TransitController(TransitService transitService) {
        this.transitService = transitService;
    }

    @PostMapping
    ResponseEntity<Transit> addTransit(@RequestBody Transit transit) {
        return transitService.addTransit(transit);
    }
    @GetMapping
    ResponseEntity<List<Transit>> getTransits(){
        return transitService.getTransits();
    }
}
