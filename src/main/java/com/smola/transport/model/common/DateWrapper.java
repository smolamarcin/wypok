package com.smola.transport.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DateWrapper {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
