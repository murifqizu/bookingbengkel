package com.bengkel.booking.services;

import java.util.List;
import java.util.Scanner;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.MemberCustomer;

public class MenuService {
    private static Scanner input = new Scanner(System.in);

    public static void run() {
        boolean isLooping = true;
        do {
            Customer loggedInCustomer = login();
            if (loggedInCustomer != null) {
                mainMenu(loggedInCustomer);
            } else {
                isLooping = false;
            }
        } while (isLooping);
    }

    public static Customer login() {
        BengkelService bengkelService = new BengkelService();
        return bengkelService.login();
    }

    public static void mainMenu(Customer loggedInCustomer) {
        String[] listMenu = {"Informasi Customer", "Booking Bengkel", "Top Up Bengkel Coin", "Informasi Booking", "Logout"};
        int menuChoice = 0;
        boolean isLooping = true;

        do {
            PrintService.printMenu(listMenu, "Booking Bengkel Menu");
            menuChoice = Validation.validasiNumberWithRange("Masukkan Pilihan Menu:", "Input Harus Berupa Angka!", "^[0-9]+$", listMenu.length - 1, 0);
            System.out.println(menuChoice);

            BengkelService bengkelService = new BengkelService();

            switch (menuChoice) {
                case 1:
                    bengkelService.displayCustomerInfo(loggedInCustomer);
                    break;
                case 2:
                    bengkelService.bookingBengkel(loggedInCustomer);
                    break;
                case 3:
                    if (loggedInCustomer instanceof MemberCustomer) {
                        bengkelService.topUpSaldoCoin(loggedInCustomer, input.nextInt());
                    } else {
                        System.out.println("Maaf, fitur ini hanya untuk Member saja!");
                    }
                    break;
                case 4:
                    List<BookingOrder> bookingOrders = loggedInCustomer.getBookingOrders();
                    if (bookingOrders.isEmpty()) {
                        System.out.println("Belum ada booking order yang dilakukan.");
                    } else {
                        System.out.println("Daftar Booking Order:");
                        for (BookingOrder bookingOrder : bookingOrders) {
                            PrintService.printBookingInfo(bookingOrder);
                            System.out.println("----------------------------------");
                        }
                    }
                    break;
                default:
                    System.out.println("Anda telah logout.");
                    isLooping = false;
                    break;
            }
        } while (isLooping);
    }
}