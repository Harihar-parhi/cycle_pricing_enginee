# Problem Breakdown

## Who is using this?
The user is a salesperson in a cycle showroom. They need a fast and simple way to calculate cycle prices without checking Excel sheets manually. Frustration happens if pricing is slow, confusing, or incorrect.

## Edge Cases
1. Part prices change over time, so same part can have different prices on different dates.
2. A part may not have pricing defined for a selected date.
3. Invalid part combinations may be selected.

## Plan
I will represent each part as an object with price history.
Each price entry will contain valid start date, end date, and price.
The pricing engine will check the selected date and pick the correct price.
The output will show component-wise breakdown and total price.

# Data Model

## Core Entities

Part
- id : String
- name : String
- component : Component
- priceHistory : List<PriceEntry>

PriceEntry
- validFrom : LocalDate
- validUntil : LocalDate
- price : double

Component
- Enum for FRAME, WHEELS, SEATING, etc.

## Relationships
A Component contains many Parts.
A Part contains multiple PriceEntry records.

## Design Decision
I used price history per part instead of one fixed price because pricing changes over time.