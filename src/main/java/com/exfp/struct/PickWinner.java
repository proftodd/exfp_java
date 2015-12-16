package com.exfp.struct;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

public class PickWinner {

	public static void main(String[] args) {
		Console c = System.console();
		ArrayList<String> contestants = new ArrayList<String>();
		Random rand = new Random();
		
		String person = "";
		while ((person = prompt(c, "Enter a name: ")).length() > 0) {
			contestants.add(person);
		}
		
		int index = rand.nextInt(contestants.size());
		c.printf("The winner is... %s.\n", contestants.get(index));
		
		System.exit(0);
	}

	public static String prompt(Console c, String prompt) {
		c.printf("%s", prompt);
		return c.readLine();
	}
}
