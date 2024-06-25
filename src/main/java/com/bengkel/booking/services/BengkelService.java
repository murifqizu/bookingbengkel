package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.models.Vehicle;
import com.bengkel.booking.repositories.CustomerRepository;


public class BengkelService {
    private Map<String, Customer> customers;
    private Scanner scanner;

    public BengkelService() {
        this.customers = new HashMap<>();
        this.scanner = new Scanner(System.in);
        List<Customer> allCustomers = CustomerRepository.getAllCustomer();
        for (Customer customer : allCustomers) {
            customers.put(customer.getCustomerId(), customer);
        }
    }
    public Customer login() {
        int attemptCount = 0;
        while (attemptCount < 3) {
            System.out.print("Masukkan Customer Id: ");
            String customerId = scanner.nextLine();
            System.out.print("Masukkan Password: ");
            String password = scanner.nextLine();

            if (customers.containsKey(customerId)) {
                Customer customer = customers.get(customerId);
                if (customer.getPassword().equals(password)) {
                    return customer;
                } else {
                    System.out.println("Password yang Anda masukkan salah!");
                }
            } else {
                System.out.println("Customer Id tidak ditemukan atau salah!");
            }
            attemptCount++;
            System.out.println("Tentatif ke-" + attemptCount + " gagal. Silakan coba lagi.");
        }
        System.out.println("Anda telah mencoba login sebanyak 3 kali. Aplikasi akan keluar.");
        System.exit(0);
        return null;
    }
    public void displayCustomerInfo(Customer customer) {
        if (customer != null) {
            System.out.println("Customer Id: " + customer.getCustomerId());
            System.out.println("Nama: " + customer.getName());
            System.out.println("Customer Status: " + customer.getStatus());
            System.out.println("Alamat: " + customer.getAddress());
        
            if (customer instanceof MemberCustomer) {
                MemberCustomer member = (MemberCustomer) customer;
                System.out.println("Saldo Koin: " + member.getSaldoCoin());
            }

            List<Vehicle> vehicles = customer.getVehicles();
            System.out.println("List Kendaraan:");
            for (Vehicle vehicle : vehicles) {
                System.out.println("- " + vehicle.getVehicleId() + " (" + vehicle.getVehicleType() + ")");
            }
        } else {
            System.out.println("Silahkan login terlebih dahulu!");
        }
    }

    public void bookingBengkel(Customer customer) {
        if (customer != null) {
            System.out.print("Masukkan Vehicle Id: ");
            String vehicleId = scanner.nextLine();
    
            Vehicle selectedVehicle = customer.findVehicleById(vehicleId);
            if (selectedVehicle == null) {
                System.out.println("Kendaraan dengan id " + vehicleId + " tidak ditemukan.");
                return;
            }

            String vehicleType = selectedVehicle.getVehicleType();
            List<String> serviceItems = getServiceItems(vehicleType);

            System.out.println("Service Items untuk kendaraan jenis " + vehicleType + ":");
            for (int i = 0; i < serviceItems.size(); i++) {
                System.out.println((i + 1) + ". " + serviceItems.get(i));
            }

            int maxNumberOfService = customer.getMaxNumberOfService();
            int numberOfServiceAllowed = customer instanceof MemberCustomer ? maxNumberOfService : 1;

            System.out.println("Masukkan nomor service item yang ingin dilakukan (1-" + serviceItems.size() + "): ");
            int selectedItemIndex = scanner.nextInt();
            scanner.nextLine();

            if (selectedItemIndex < 1 || selectedItemIndex > serviceItems.size()) {
                System.out.println("Nomor service item tidak valid.");
                return;
            }

            String selectedServiceItem = serviceItems.get(selectedItemIndex - 1);
            System.out.println("Anda memilih service item: " + selectedServiceItem);

            System.out.print("Masukkan metode pembayaran (1 untuk Cash, 2 untuk Saldo Coin): ");
            int paymentMethod = scanner.nextInt();
            scanner.nextLine();

            double totalPayment = calculateTotalPayment(selectedServiceItem);
            double discount = 0.0;

            if (customer instanceof MemberCustomer && paymentMethod == 2) {
                discount = totalPayment * 0.1;
                totalPayment -= discount;
                MemberCustomer member = (MemberCustomer) customer;
                member.setSaldoCoin(member.getSaldoCoin() - (int) totalPayment);
                System.out.println("Pembayaran menggunakan saldo coin berhasil. Diskon 10% telah diterapkan.");
                System.out.println("Sisa Saldo Koin: " + member.getSaldoCoin());
            } else if (paymentMethod == 1) {
                System.out.println("Pembayaran menggunakan Cash.");
            } else {
                System.out.println("Metode pembayaran tidak valid.");
                return;
            }

            System.out.println("Total yang harus dibayar: " + totalPayment);

        } else {
            System.out.println("Silahkan login terlebih dahulu!");
        }
    }

    public void topUpSaldoCoin(Customer customer, int amount) {
        if (customer instanceof MemberCustomer) {
            MemberCustomer member = (MemberCustomer) customer;
            member.setSaldoCoin(member.getSaldoCoin() + amount);
            System.out.println("Top-up saldo coin sebesar " + amount + " berhasil.");
            System.out.println("Sisa Saldo Koin: " + member.getSaldoCoin());
        } else {
            System.out.println("Maaf, fitur ini hanya untuk Member saja!");
        }
    }


     public void logout() {
        System.out.println("Anda telah logout.");
    }

    private List<String> getServiceItems(String vehicleType) {
        List<String> serviceItems = new ArrayList<>();
        if (vehicleType.equalsIgnoreCase("Car")) {
            serviceItems.add("Ganti Oli");
            serviceItems.add("Tune Up");
        } else if (vehicleType.equalsIgnoreCase("Motorcycle")) {
            serviceItems.add("Ganti Kampas Rem");
            serviceItems.add("Service Mesin");
        }
        return serviceItems;
    }

    private double calculateTotalPayment(String serviceItem) {
        if (serviceItem.equalsIgnoreCase("Ganti Oli")) {
            return 200.0;
        } else if (serviceItem.equalsIgnoreCase("Tune Up")) {
            return 300.0;
        } else if (serviceItem.equalsIgnoreCase("Ganti Kampas Rem")) {
            return 150.0;
        } else if (serviceItem.equalsIgnoreCase("Service Mesin")) {
            return 500.0;
        } else {
            return 0.0;
        }
    }
}