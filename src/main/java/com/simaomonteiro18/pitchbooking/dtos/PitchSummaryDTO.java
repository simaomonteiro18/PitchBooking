package com.simaomonteiro18.pitchbooking.dtos;

import com.simaomonteiro18.pitchbooking.entities.enums.PitchType;

public class PitchSummaryDTO {

    private String name;
    private String city;
    private double pricePerHour;
    private PitchType type;

    public PitchSummaryDTO() {
    }

    public PitchSummaryDTO(String name, String city, double pricePerHour, PitchType type) {
        this.name = name;
        this.city = city;
        this.pricePerHour = pricePerHour;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public PitchType getType() {
        return type;
    }

}
