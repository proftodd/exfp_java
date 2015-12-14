package com.exfp;

import java.io.Console;

public class TipCalculator {

	private static class BillInfo {
		String billAmountString;
		String tipRateString;
	}

	public static void main(String [] args) {
		Console c = System.console();

		double billAmount = Double.MIN_VALUE;
		int tipRate = Integer.MIN_VALUE;
		double tip = 0.0;
		double total = 0.0;

		BillInfo info = prompt(c);

		while (billAmount == Double.MIN_VALUE || tipRate == Integer.MIN_VALUE) {
			try {
				billAmount = Double.parseDouble(info.billAmountString);
				tipRate = Integer.parseInt(info.tipRateString);
				while (billAmount < 0 || tipRate < 0) {
					System.out.println("Bill amount and tip rate must be greater than zero");
					info = prompt(c);
					billAmount = Double.parseDouble(info.billAmountString);
					tipRate = Integer.parseInt(info.tipRateString);
				}
			} catch (NumberFormatException e) {
				System.out.println("Bill amount must be a dollar amount, tip rate must be an integer");
				info = prompt(c);
			}
		}

		tip = billAmount * ((double) tipRate / 100);
		tip = round(tip, 2);
		total = billAmount + tip;

		System.out.printf("Tip: $%.2f\n", tip);
		System.out.printf("Total: $%.2f\n", total);

		System.exit(0);
	}

	private static BillInfo prompt(Console c) {
		BillInfo info = new BillInfo();
		System.out.print("What is the bill amount? ");
		info.billAmountString = c.readLine();
		System.out.print("What is the tip rate? ");
		info.tipRateString = c.readLine();
		return info;
	}

	private static double round(double num, int decPlaces) {
		double power = Math.pow(10, decPlaces);
		double digits = num * power;
		return Math.ceil(digits) / power;
	}
}
