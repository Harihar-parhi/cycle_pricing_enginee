package com.cycles.pricing;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PricingEngine {

    public Map<Component, Double> calculateBreakdown(List<Part> selectedParts, LocalDate date) {
        Map<Component, Double> breakdown = new HashMap<>();

        for (Part part : selectedParts) {
            double price = part.getPriceForDate(date);
            Component component = part.getComponent();

            breakdown.put(component,
                    breakdown.getOrDefault(component, 0.0) + price);
        }

        return breakdown;
    }

    public double calculateTotal(Map<Component, Double> breakdown) {
        double total = 0;

        for (double amount : breakdown.values()) {
            total += amount;
        }

        return total;
    }
}