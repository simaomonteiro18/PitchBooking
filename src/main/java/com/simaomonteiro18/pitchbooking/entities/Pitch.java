package com.simaomonteiro18.pitchbooking.entities;

import com.simaomonteiro18.pitchbooking.entities.enums.PitchType;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pitches")
public class Pitch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private double pricePerHour;

    @Enumerated(EnumType.STRING)
    private PitchType pitchType;

    public Pitch() {

    }

    public Pitch(String name, String city, double pricePerHour, PitchType pitchType) {
        this.name = name;
        this.city = city;
        this.pricePerHour = pricePerHour;
        this.pitchType = pitchType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public PitchType getPitchType() {
        return pitchType;
    }

    public void setPitchType(PitchType pitchType) {
        this.pitchType = pitchType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch = (Pitch) o;
        return Objects.equals(id, pitch.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pitch{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", pricePerHour=" + pricePerHour +
                ", pitchType=" + pitchType +
                '}';
    }
    
}
