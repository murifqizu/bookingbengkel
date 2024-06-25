package com.bengkel.booking.models;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String name;
    private String address;
    private String password;
    private List<Vehicle> vehicles;
    private String status;
    private List<BookingOrder> bookingOrders;
    private int maxNumberOfService;

    public Customer(String customerId, String name, String address, String password, List<Vehicle> vehicles, String status) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.password = password;
        this.vehicles = vehicles;
        this.status = status;
        this.bookingOrders = new ArrayList<>();
        this.maxNumberOfService = 1;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMaxNumberOfService() {
        return maxNumberOfService;
    }

    public void setMaxNumberOfService(int maxNumberOfService) {
        this.maxNumberOfService = maxNumberOfService;
    }

    public List<BookingOrder> getBookingOrders() {
        return bookingOrders;
    }

    public void addBookingOrder(BookingOrder bookingOrder) {
        bookingOrders.add(bookingOrder);
    }

    public Vehicle findVehicleById(String vehicleId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equals(vehicleId)) {
                return vehicle;
            }
        }
        return null;
    }
}