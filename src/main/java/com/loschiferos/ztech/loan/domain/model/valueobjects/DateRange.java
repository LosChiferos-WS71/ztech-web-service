package com.loschiferos.ztech.loan.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record DateRange (String startDate, String endDate){

    public DateRange() {
        this(null, null);
    }

    public String getDateRange() {
        return "DateRange[startDate=" + startDate + ", endDate=" + endDate + "]";
    }
}
