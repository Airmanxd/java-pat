package com.example.Practice14;

public class Departure {
    private String type;
    private String departureDate;

    public Departure(String type, String departureDate) {
        this.type = type;
        this.departureDate = departureDate;
    }

    public String getType() {
        return type;
    }

    public String getDepartureDate() {
        return departureDate;
    }
}
