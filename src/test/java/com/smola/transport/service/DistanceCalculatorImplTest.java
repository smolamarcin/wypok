package com.smola.transport.service;

import com.google.maps.errors.ApiException;
import com.google.maps.model.Distance;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class DistanceCalculatorImplTest {
    private DistanceCalculatorImpl distanceCalculator = new DistanceCalculatorImpl();
    @Test
    public void shouldCalculateDistance() throws InterruptedException, ApiException, IOException {
        //given
        String sourceAddress = "ul. Zakręt 8, Poznań";
        String destinationAddress = "Złota 44, Warszawa";
        //when
        Distance distance = distanceCalculator.calculate(sourceAddress, destinationAddress);
        //then
        assertEquals(distance.inMeters,1000L);
    }
}