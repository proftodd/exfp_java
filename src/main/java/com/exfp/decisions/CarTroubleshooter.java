package com.exfp.decisions;

import java.io.Console;

public class CarTroubleshooter {

	public static void main(String[] args) {
		Console c = System.console();
		if (getAnswer(c, "Is the car silent when you turn the key? ")) {
			if (getAnswer(c, "Are the battery terminals corroded? ")) {
				c.printf("Clean terminals and try starting again.\n");
			} else {
				c.printf("Replace cables and try again.\n");
			}
		} else {
			if (getAnswer(c, "Does the car make a clicking noise? ")) {
				c.printf("Replace the battery.\n");
			} else if (getAnswer(c, "Does the car crank up but fail to start? ")) {
				c.printf("Check spark plug connections.\n");
			} else if (getAnswer(c, "Does the engine start and then die? ")) {
				if (getAnswer(c, "Does your car have fuel injection? ")) {
					c.printf("Check to ensure the choke is opening and closing.\n");
				} else {
					c.printf("Get it in for service.\n");
				}
			} else {
				c.printf("See your mechanic.\n");
			}
		}
	}
	
	public static boolean getAnswer(Console c, String question) {
		c.printf(question);
		String answer = c.readLine().toLowerCase();
		return answer.startsWith("y");
	}
}
