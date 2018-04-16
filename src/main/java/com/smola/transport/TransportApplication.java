package com.smola.transport;

import com.smola.transport.model.Transit;
import com.smola.transport.repository.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
@ComponentScan(basePackages = "com.smola.transport")
public class TransportApplication implements CommandLineRunner {
    @Autowired
    private TransitRepository transitRepository;

    public static void main(String[] args) {
        SpringApplication.run(TransportApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Transit transit = createSampleTransit();
        transitRepository.save(transit);
    }

    private static Transit createSampleTransit() {
        String sourceAddress = "ul. Zakręt 8, Poznań";
        String destinationAddress = "ul. Złota 44, Warszawa";
        BigDecimal price = new BigDecimal("22");
        LocalDateTime localDate = LocalDateTime.now();
        Transit transit = new Transit();
        transit.setSourceAddress(sourceAddress);
        transit.setDestinationAddress(destinationAddress);
        transit.setPrice(price);
        transit.setDate(localDate);
//        return new Transit.TransitBuilder(sourceAddress, destinationAddress).setPrice(price).setDate(localDate).build();
        return transit;
    }

}
