package com.smola.transport.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @PostMapping("/daily")
    ResponseEntity<?> getDailyReport(){
        return ResponseEntity.ok().build();
    }

}
