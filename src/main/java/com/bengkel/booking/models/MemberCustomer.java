package com.bengkel.booking.models;

import java.util.List;

public class MemberCustomer extends Customer {
    private int saldoCoin;

    public MemberCustomer(String customerId, String name, String address, String password, List<Vehicle> vehicles, String status, int saldoCoin) {
        super(customerId, name, address, password, vehicles, status);
        this.saldoCoin = saldoCoin;
        setMaxNumberOfService(2);
    }

    public int getSaldoCoin() {
        return saldoCoin;
    }

    public void setSaldoCoin(int saldoCoin) {
        this.saldoCoin = saldoCoin;
    }
}
