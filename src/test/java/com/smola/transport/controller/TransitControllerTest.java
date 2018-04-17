package com.smola.transport.controller;

import com.google.gson.Gson;
import com.smola.transport.model.Transit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class TransitControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private String END_POINT = "/transits";

    @Test
    public void shouldReturn_http400_whenTryingToAdd_InvalidTransit() throws Exception {
        //given
        Transit invalidTransit = createInvalidTransit();
        Gson gson = new Gson();
        String transitJSON = gson.toJson(invalidTransit);

        //when - then
        this.mockMvc.perform(post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(transitJSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturn_http201_whenTryingToAdd_CorrectTransit() throws Exception {
        //given
        Transit correctTransit = createCorrectTransit();
        Gson gson = new Gson();
        String transitJSON = gson.toJson(correctTransit);
        //when - then
        this.mockMvc.perform(post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(transitJSON))
                .andExpect(status().isCreated());
    }

    private Transit createInvalidTransit() {
        Transit transit = new Transit();
        transit.setSourceAddress("sdsdsd");
        transit.setDestinationAddress("xddd");

        return transit;
    }

    private Transit createCorrectTransit() {
        Transit correctTransit = new Transit();
        correctTransit.setSourceAddress("ul. Zakręt 8, Poznań");
        correctTransit.setDestinationAddress("Złota 44, Warszawa");
        correctTransit.setDate(LocalDate.of(2017, 2, 21));
        correctTransit.setPrice(BigDecimal.valueOf(23));
        return correctTransit;
    }
}