package com.smola.transport.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransitTest {

    @Test
    public void shouldCreateProperObject() {
        //given
        SourceAddress sourceAddress = new SourceAddress("ul. Zakręt 8, Poznań");
        DestinationAddress destinationAddress = new DestinationAddress("Złota 44, Warszawa");
        Price price = new Price("22");
        Date date = new Date("22.04.2017");

        //when
        Transit transit = new Transit.TransitBuilder(sourceAddress,destinationAddress).setPrice(price).setDate(date).build();
        //then
        assertEquals(transit.getDate(),date);
        assertEquals(transit.getDestinationAddress(),destinationAddress);
        assertEquals(transit.getSourceAddress(),sourceAddress);
        assertEquals(transit.getPrice(),price);
    }
}