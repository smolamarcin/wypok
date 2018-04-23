package com.smola.transport.controller;

import com.smola.transport.model.common.Distance;
import com.smola.transport.model.common.Transit;
import com.smola.transport.repository.TransitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;
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

    @MockBean
    private TransitRepository transitRepository;

    @Test
    public void shouldReturn_SummaryReport() throws Exception {
        when(transitRepository.findAll()).thenReturn(dummyData());

        this.mockMvc.perform(get(END_POINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(60)))
                .andExpect(jsonPath("$.distance.meters",is(300)));
    }

    private List<Transit> dummyData() {
        Transit transit = createTransit();
        Transit transit2 = createTransit();
        Transit transit3 = createTransit();
        return Arrays.asList(transit,transit2,transit3);
    }

    private Transit createTransit() {
        Transit correctTransit = new Transit();
        correctTransit.setSourceAddress("ul. Zakręt 8, Poznań");
        correctTransit.setDestinationAddress("Złota 44, Warszawa");
        correctTransit.setDistance(new Distance(100));
        correctTransit.setDate(LocalDate.of(2017, 2, 21));
        correctTransit.setPrice(BigDecimal.valueOf(20));
        return correctTransit;
    }
}