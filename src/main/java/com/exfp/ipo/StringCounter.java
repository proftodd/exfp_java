package com.exfp.ipo;

import java.io.Console;

public class StringCounter {

	public static void main(String [] args) {
	
		Console c = System.console();
		
		System.out.print("What is the input string? ");
		String input = c.readLine();
		while (input.length() == 0) {
			System.out.println("Please enter something");
			System.out.print("What is the input string? ");
			input = c.readLine();
		}
		System.out.printf("%s has %d characters.\n", input, input.length());
		
		System.exit(0);
	}
}
