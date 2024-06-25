package com.bengkel.booking.services;

import java.util.List;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Car;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.models.Vehicle;

public class PrintService {
	
	public static void printMenu(String[] listMenu, String title) {
		String line = "+---------------------------------+";
		int number = 1;
		String formatTable = " %-2s. %-25s %n";
		
		System.out.printf("%-25s %n", title);
		System.out.println(line);
		
		for (String data : listMenu) {
			if (number < listMenu.length) {
				System.out.printf(formatTable, number, data);
			}else {
				System.out.printf(formatTable, 0, data);
			}
			number++;
		}
		System.out.println(line);
		System.out.println();
	}
	
	public static void printVechicle(List<Vehicle> listVehicle) {
		String formatTable = "| %-2s | %-15s | %-10s | %-15s | %-15s | %-5s | %-15s |%n";
		String line = "+----+-----------------+------------+-----------------+-----------------+-------+-----------------+%n";
		System.out.format(line);
	    System.out.format(formatTable, "No", "Vechicle Id", "Warna", "Brand", "Transmisi", "Tahun", "Tipe Kendaraan");
	    System.out.format(line);
	    int number = 1;
	    String vehicleType = "";
	    for (Vehicle vehicle : listVehicle) {
	    	if (vehicle instanceof Car) {
				vehicleType = "Mobil";
			}else {
				vehicleType = "Motor";
			}
	    	System.out.format(formatTable, number, vehicle.getVehicleId(), vehicle.getColor(), vehicle.getBrand(), vehicle.getTransmissionType(), vehicle.getYearRelease(), vehicleType);
	    	number++;
	    }
	    System.out.printf(line);
	}
	
	public static void printCustomerDetail(Customer customer) {
		System.out.println("Detail Customer:");
		System.out.println("Customer Id: " + customer.getCustomerId());
		System.out.println("Nama: " + customer.getName());
		System.out.println("Alamat: " + customer.getAddress());
		System.out.println("Status Keanggotaan: " + customer.getStatus());
		if (customer instanceof MemberCustomer) {
			MemberCustomer member = (MemberCustomer) customer;
			System.out.println("Saldo Coin: " + member.getSaldoCoin());
		}
		List<Vehicle> vehicles = customer.getVehicles();
		System.out.println("List Kendaraan:");
		for (Vehicle vehicle : vehicles) {
			System.out.println("- " + vehicle.getVehicleId() + " (" + vehicle.getVehicleType() + ")");
		}
	}

	public static void printTopUpInfo(MemberCustomer member, int amount) {
		System.out.println("Informasi Top Up Saldo Coin:");
		System.out.println("Customer: " + member.getName());
		System.out.println("Jumlah Top Up: " + amount);
		System.out.println("Saldo Coin Sekarang: " + member.getSaldoCoin());
	}

	 public static void printBookingInfo(BookingOrder bookingOrder) {
        System.out.println("Booking Id: " + bookingOrder.getBookingId());
        System.out.println("Customer: " + bookingOrder.getCustomer().getName());
        System.out.println("Total Payment: " + bookingOrder.getTotalPayment());
	}
}
