package com.cycles.pricing;

import java.time.LocalDate;

public class PriceEntry {
    private LocalDate validFrom;
    private LocalDate validUntil;
    private double price;

    public PriceEntry(LocalDate validFrom, LocalDate validUntil, double price) {
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.price = price;
    }

    public boolean isValidFor(LocalDate date) {
        boolean afterStart = !date.isBefore(validFrom);
        boolean beforeEnd = (validUntil == null || !date.isAfter(validUntil));
        return afterStart && beforeEnd;
    }

    public double getPrice() {
        return price;
    }
}