package com.exfp.ipo;

import java.io.Console;

public class Greeter {

	public static void main(String [] args) {
		Console c = System.console();
		
		System.out.print("What is your name? ");
		String name = c.readLine();
		System.out.printf("Hello, %s, nice to meet you!\n", name);
		
		System.exit(0);
	}
}
