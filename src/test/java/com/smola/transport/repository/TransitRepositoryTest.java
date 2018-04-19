package com.smola.transport.repository;

import com.smola.transport.model.common.Transit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransitRepositoryTest {
    @Autowired
    private TransitRepository transitRepository;

    @Before
    public void setUp() {
        fillUpDbWithDummyData();
    }

    @Test
    public void shouldReturnTransitsBetweenDate() {
        LocalDate startDate = LocalDate.of(1994, 7, 21);
        LocalDate endDate = LocalDate.of(1994, 7, 22);
        List<Transit> transitsBetweenDates = transitRepository.findByDateBetween(startDate,endDate);

        for (Transit transit : transitsBetweenDates) {
            LocalDate date = transit.getDate();
            assertEquals(1994, date.getYear());
        }
        assertEquals(2, transitsBetweenDates.size());
    }

    private void fillUpDbWithDummyData() {
        Transit transit1 = new Transit();
        transit1.setDate(LocalDate.of(1994, 7, 21));
        Transit transit2 = new Transit();
        transit2.setDate(LocalDate.of(1994, 7, 22));
        Transit transit3 = new Transit();
        transit3.setDate(LocalDate.of(1995, 7, 23));

        transitRepository.save(transit1);
        transitRepository.save(transit2);
        transitRepository.save(transit3);
    }
}