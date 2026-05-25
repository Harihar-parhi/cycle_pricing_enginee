package com.cycles.pricing;

import java.time.LocalDate;
import java.util.List;

public class Part {
    private String id;
    private String name;
    private Component component;
    private List<PriceEntry> priceHistory;

    public Part(String id, String name, Component component, List<PriceEntry> priceHistory) {
        this.id = id;
        this.name = name;
        this.component = component;
        this.priceHistory = priceHistory;
    }

    public double getPriceForDate(LocalDate date) {
        for (PriceEntry entry : priceHistory) {
            if (entry.isValidFor(date)) {
                return entry.getPrice();
            }
        }
        throw new RuntimeException("No price found for part: " + name);
    }

    public Component getComponent() {
        return component;
    }

    public String getId() {
        return id;
    }
}