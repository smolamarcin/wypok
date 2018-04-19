package com.smola.transport.controller;

import com.smola.transport.model.Transit;
import com.smola.transport.repository.TransitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ReportControllerImplTest {

    private static final String END_POINT = "/reports";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransitRepository transitRepository;

    @Before
    public void setUp() {
        fillUpDbWithDummyData();
    }

    @Test
    public void shouldReturn_SummaryReport() throws Exception {
        this.mockMvc.perform(get(END_POINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(60.0)));
//                .andExpect(jsonPath("$.distance.meters",is(300_000)));
    }

    private void fillUpDbWithDummyData() {
        Transit transit = createTransit();
        Transit transit2 = createTransit();
        Transit transit3 = createTransit();
        transitRepository.save(transit);
        transitRepository.save(transit2);
        transitRepository.save(transit3);
    }

    private Transit createTransit() {
        Transit correctTransit = new Transit();

        correctTransit.setSourceAddress("ul. Zakręt 8, Poznań");
        correctTransit.setDestinationAddress("Złota 44, Warszawa");

        correctTransit.setDate(LocalDate.of(2017, 2, 21));
        correctTransit.setPrice(BigDecimal.valueOf(20));
        return correctTransit;
    }
}