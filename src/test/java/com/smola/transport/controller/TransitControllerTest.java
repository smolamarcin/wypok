package com.smola.transport.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String transitJSON = objectMapper.writeValueAsString(invalidTransit);

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

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        String transitJSON = objectMapper.writeValueAsString(correctTransit);
        //when - then
        this.mockMvc.perform(post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(transitJSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturn_http200_whenTryingToRetrieveAllTransits() throws Exception {
        this.mockMvc.perform(get(END_POINT))
                .andExpect(status().isOk());
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