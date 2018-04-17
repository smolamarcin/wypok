package com.smola.transport;

import com.smola.transport.model.Transit;
import com.smola.transport.repository.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
@ComponentScan(basePackages = "com.smola.transport")
public class TransportApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportApplication.class, args);
    }




}
