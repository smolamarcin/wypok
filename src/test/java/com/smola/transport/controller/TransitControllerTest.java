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

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class TransitControllerTest {
    private static final String END_POINT = "/transits";
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn_http201_whenPosToEndpoint() throws Exception {
        //given
        Gson gson = new Gson();
        Transit transit = createSampleTransit();
        String transitJSON = gson.toJson(transit);

        //when - then
        this.mockMvc.perform(post(END_POINT)
                .contentType(contentType)
                .content(transitJSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sourceAddress", is("ul. Zakręt 8, Poznań")));

    }

    private Transit createSampleTransit() {
        String sourceAddress ="ul. Zakręt 8, Poznań";
        String destinationAddress = "Złota 44, Warszawa";
        BigDecimal price = new BigDecimal("22");
        String localDate ="1994-04-01";
        return new Transit.TransitBuilder(sourceAddress, destinationAddress).setPrice(price).setDate(localDate).build();
    }
}