package com.bengkel.booking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Vehicle {
    private String vehicleId;
    private String color;
    private String brand;
    private String transmissionType;
    private int yearRelease;
    private String vehicleType;
    public abstract String getCategory();
}