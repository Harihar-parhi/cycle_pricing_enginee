package com.cycles.pricing;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        String content = Files.readString(Paths.get("src/main/resources/input.json"));
        JSONObject json = new JSONObject(content);

        LocalDate pricingDate = LocalDate.parse(json.getString("date"));
        JSONArray partsArray = json.getJSONArray("parts");

        Map<String, Part> availableParts = new HashMap<>();

        availableParts.put("steel_frame", new Part(
                "steel_frame",
                "Steel Frame",
                Component.FRAME,
                Arrays.asList(
                        new PriceEntry(LocalDate.of(2016, 1, 1), null, 1200)
                )
        ));

        availableParts.put("tubeless_tyre", new Part(
                "tubeless_tyre",
                "Tubeless Tyre",
                Component.WHEELS,
                Arrays.asList(
                        new PriceEntry(LocalDate.of(2016, 1, 1),
                                LocalDate.of(2016, 11, 30), 200),
                        new PriceEntry(LocalDate.of(2016, 12, 1),
                                null, 230)
                )
        ));

        availableParts.put("spokes", new Part(
                "spokes",
                "Spokes",
                Component.WHEELS,
                Arrays.asList(
                        new PriceEntry(LocalDate.of(2016, 1, 1), null, 300)
                )
        ));

        List<Part> selectedParts = new ArrayList<>();

        for (int i = 0; i < partsArray.length(); i++) {
            String partId = partsArray.getString(i);
            selectedParts.add(availableParts.get(partId));
        }

        PricingEngine engine = new PricingEngine();

        Map<Component, Double> breakdown =
                engine.calculateBreakdown(selectedParts, pricingDate);

        double total = engine.calculateTotal(breakdown);

        System.out.println("Cycle Price Breakdown");
        System.out.println("----------------------");

        for (Map.Entry<Component, Double> entry : breakdown.entrySet()) {
            System.out.println(entry.getKey() + " : ₹" + entry.getValue());
        }

        System.out.println("----------------------");
        System.out.println("TOTAL : ₹" + total);
    }
}