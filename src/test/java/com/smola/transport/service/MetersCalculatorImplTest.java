package com.smola.transport.service;

import com.google.maps.model.Distance;
import com.smola.transport.model.Meters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MetersCalculatorImplTest {

    @Autowired
    private DistanceCalculator distanceCalculator;

    @Test
    public void shouldCalculateDistance() {
        //given
        String sourceAddress = "ul. Zakręt 8, Poznań";
        String destinationAddress = "Złota 44, Warszawa";
        //when
        Optional<Meters> distance = distanceCalculator.calculate(sourceAddress, destinationAddress);
        //then
        assertEquals(300_000, distance.get().getMeters(),30_000);
    }

}