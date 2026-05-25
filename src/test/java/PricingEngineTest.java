package com.cycles.pricing;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingEngineTest {

    @Test
    void testOldPriceBeforeDecember() {
        Part tyre = new Part(
                "tubeless_tyre",
                "Tubeless Tyre",
                Component.WHEELS,
                Arrays.asList(
                        new PriceEntry(LocalDate.of(2016, 1, 1),
                                LocalDate.of(2016, 11, 30), 200),
                        new PriceEntry(LocalDate.of(2016, 12, 1),
                                null, 230)
                )
        );

        PricingEngine engine = new PricingEngine();

        Map<Component, Double> result =
                engine.calculateBreakdown(
                        List.of(tyre),
                        LocalDate.of(2016, 10, 15)
                );

        assertEquals(200, result.get(Component.WHEELS));
    }

    @Test
    void testNewPriceAfterDecember() {
        Part tyre = new Part(
                "tubeless_tyre",
                "Tubeless Tyre",
                Component.WHEELS,
                Arrays.asList(
                        new PriceEntry(LocalDate.of(2016, 1, 1),
                                LocalDate.of(2016, 11, 30), 200),
                        new PriceEntry(LocalDate.of(2016, 12, 1),
                                null, 230)
                )
        );

        PricingEngine engine = new PricingEngine();

        Map<Component, Double> result =
                engine.calculateBreakdown(
                        List.of(tyre),
                        LocalDate.of(2016, 12, 15)
                );

        assertEquals(230, result.get(Component.WHEELS));
    }

    @Test
    void testTotalCalculation() {
        PricingEngine engine = new PricingEngine();

        Map<Component, Double> data = Map.of(
                Component.FRAME, 1200.0,
                Component.WHEELS, 530.0
        );

        assertEquals(1730.0, engine.calculateTotal(data));
    }
}