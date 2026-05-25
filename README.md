# Cycle Pricing Engine

A Java-based pricing engine for Hero Cycles configuration pricing.

## Features
- Time-sensitive pricing
- JSON input support
- Component-wise price breakdown
- Total price calculation
- Unit tests

## How to Run
1. Open project in IntelliJ IDEA
2. Ensure Maven dependencies are synced
3. Edit `src/main/resources/input.json`
4. Run `Main.java`

## Example Input
```json
{
  "date": "2016-12-15",
  "parts": [
    "steel_frame",
    "tubeless_tyre",
    "spokes"
  ]
}
```

## Example Output
Cycle Price Breakdown
FRAME : ₹1200
WHEELS : ₹530
TOTAL : ₹1730