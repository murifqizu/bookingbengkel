package com.bengkel.booking.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Validation {
	
	public static String validasiInput(String question, String errorMessage, String regex) {
	    Scanner input = new Scanner(System.in);
	    String result;
	    boolean isLooping = true;
	    do {
	      System.out.print(question);
	      result = input.nextLine();
	      if (result.matches(regex)) {
	        isLooping = false;
	      }else {
	        System.out.println(errorMessage);
	      }

	    } while (isLooping);
	    return result;
	  }
	
	public static int validasiNumberWithRange(String question, String errorMessage, String regex, int max, int min) {
	    int result;
	    boolean isLooping = true;
	    do {
	      result = Integer.valueOf(validasiInput(question, errorMessage, regex));
	      if (result >= min && result <= max) {
	        isLooping = false;
	      }else {
	        System.out.println("Pilihan angka " + min + " s.d " + max);
	      }
	    } while (isLooping);

	    return result;
	  }

	public static LocalDate validateDateInput(String question, String errorMessage) {
		boolean isLooping = true;
		LocalDate result = null;
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		do {
			String userInput = validasiInput(question, errorMessage, "\\d{4}-\\d{2}-\\d{2}");

			try {
				result = LocalDate.parse(userInput, dateFormatter);
				isLooping = false;
			} catch (DateTimeParseException e) {
				System.out.println(errorMessage);
			}

		} while (isLooping);

		return result;
	}
}