package com.exfp.ipo;

import java.io.Console;

public class GreeterNoVar {

	public static void main(String [] args) {
		
		System.out.print("What is your name? ");
		System.out.printf("Hello, %s, nice to meet you!\n", System.console().readLine());
		
		System.exit(0);
	}
}
