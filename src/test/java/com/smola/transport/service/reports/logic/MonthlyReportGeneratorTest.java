package com.smola.transport.service.reports.logic;

import com.smola.transport.model.common.Distance;
import com.smola.transport.model.common.Transit;
import com.smola.transport.model.reports.DailyReport;
import com.smola.transport.model.reports.MonthlyReport;
import com.smola.transport.repository.TransitRepository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MonthlyReportGeneratorTest {
    private static final BigDecimal SAMPLE_PRICE_PER_DAY = BigDecimal.valueOf(10);
    private static final Distance SAMPLE_DISTANCE_PER_DAY = new Distance(100);
    private TransitRepository transitRepository;
    private MonthlyReportGenerator monthlyReportGenerator;


    @Before
    public void setUp() {
        transitRepository = mock(TransitRepository.class);
        monthlyReportGenerator = new MonthlyReportGenerator(transitRepository,
                mock(SummaryPriceCalculator.class),
                mock(SummaryDistanceCalculator.class)
                );
    }

    @Test
    public void shouldReturn_reportsFromFirstOfMonth_toDayEarlier() {
        //given
        LocalDate today = LocalDate.of(2017, 12, 5);
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        when(transitRepository.findByDateBetween(firstDayOfMonth, today)).thenReturn(dummyDataWithDifferentDates());

        //when
        MonthlyReport reports = monthlyReportGenerator.calculate(today);

        //then
        List<DailyReport> dailyReports = reports.getDailyReports();
        assertEquals(4, dailyReports.size());
        assertTrue(dailyReports.contains(new DailyReport(LocalDate.of(2017, 12, 1))));
        assertTrue(dailyReports.contains(new DailyReport(LocalDate.of(2017, 12, 2))));
        assertTrue(dailyReports.contains(new DailyReport(LocalDate.of(2017, 12, 3))));
        assertTrue(dailyReports.contains(new DailyReport(LocalDate.of(2017, 12, 4))));
    }

    @Test
    public void shouldReturn_summaryPriceAndDistance_forEveryDayInMonth() {
        //given
        LocalDate date = LocalDate.of(2017, 12, 5);
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        when(transitRepository.findByDateBetween(firstDayOfMonth, date)).thenReturn(dummyDataWithDifferentDates());

        //when
        MonthlyReport reports = monthlyReportGenerator.calculate(date);

        //then
        List<DailyReport> dailyReports = reports.getDailyReports();
        for (DailyReport dailyReport : dailyReports) {
            assertEquals(SAMPLE_DISTANCE_PER_DAY, dailyReport.getDistance());
            assertEquals(SAMPLE_PRICE_PER_DAY, dailyReport.getPrice());
        }
    }

    @Test
    public void shouldReturn_summaryPriceAndDistance_forEveryDayInMont_whenAllTransitsHasSameDate() {
        //given
        LocalDate date = LocalDate.of(2017, 12, 5);
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        when(transitRepository.findByDateBetween(firstDayOfMonth, date)).thenReturn(dummyDataWithSameDates());

        //when
        MonthlyReport reports = monthlyReportGenerator.calculate(date);

        //then
        List<DailyReport> dailyReports = reports.getDailyReports();
        assertEquals(1, dailyReports.size());
        for (DailyReport dailyReport : dailyReports) {
            assertEquals(SAMPLE_PRICE_PER_DAY.multiply(BigDecimal.valueOf(4)), dailyReport.getPrice());
        }
    }

    private List<Transit> dummyDataWithDifferentDates() {
        List<Transit> transits = new ArrayList<>();
        transits.add(new Transit());
        transits.add(new Transit());
        transits.add(new Transit());
        transits.add(new Transit());
        for (Transit transit : transits) {
            transit.setDistance(SAMPLE_DISTANCE_PER_DAY);
            transit.setPrice(SAMPLE_PRICE_PER_DAY);
        }
        for (int i = 0; i < transits.size(); i++) {
            transits.get(i).setDate(LocalDate.of(2017, 12, i + 1));
        }
        return transits;
    }

    private List<Transit> dummyDataWithSameDates() {
        List<Transit> transits = new ArrayList<>();
        transits.add(new Transit());
        transits.add(new Transit());
        transits.add(new Transit());
        transits.add(new Transit());
        for (Transit transit : transits) {
            transit.setDistance(SAMPLE_DISTANCE_PER_DAY);
            transit.setPrice(SAMPLE_PRICE_PER_DAY);
            transit.setDate(LocalDate.of(2017, 12, 2));
        }
        return transits;
    }
}