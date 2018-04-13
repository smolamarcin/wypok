package com.smola.transport.controller;

import com.google.gson.Gson;
import com.smola.transport.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class TransitControllerTest {
    private static final String END_POINT = "/transits";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn_http201_whenPostNewTransit() throws Exception {
        this.mockMvc.perform(post(END_POINT))
                .andExpect(status().isCreated());
    }


    private Transit createSampleTransit() {
        SourceAddress sourceAddress = new SourceAddress("ul. Zakręt 8, Poznań");
        DestinationAddress destinationAddress = new DestinationAddress("Złota 44, Warszawa");
        Price price = new Price("22");
        Date date = new Date("22.04.2017");

        return new Transit.TransitBuilder(sourceAddress, destinationAddress).setPrice(price).setDate(date).build();
    }
}