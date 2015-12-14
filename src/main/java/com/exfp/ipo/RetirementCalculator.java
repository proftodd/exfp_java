package com.exfp.ipo;

import java.io.Console;
import java.util.Calendar;

public class RetirementCalculator {

	public static void main(String [] args) {
		Console c = System.console();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		System.out.print("What is your current age? ");
		String ageString = c.readLine();
		System.out.print("At what age would you like to retire? ");
		String retirementAgeString = c.readLine();
		
		int currentAge = Integer.parseInt(ageString);
		int retirementAge = Integer.parseInt(retirementAgeString);
		if (currentAge < retirementAge) {
			int difference = retirementAge - currentAge;
			System.out.printf("You have %d years left until you can retire.\n", difference);
			System.out.printf("It's %d, so you can retire in %d.\n", year, year + difference);
		} else {
			System.out.println("You can retire anytime!");
		}
		
		System.exit(0);
	}
}
