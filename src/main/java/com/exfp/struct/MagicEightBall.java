package com.exfp.struct;

import java.io.Console;
import java.util.Random;

public class MagicEightBall {
	
	static String [] answers = {
		"It is certain",
		"It is decidedly so",
		"Without a doubt",
		"Yes, definitely",
		"You may rely on it",
		"As I see it, yes",
		"Most likely",
		"Outlook good",
		"Yes",
		"Signs point to yes",
		"Reply hazy try again",
		"Ask again later",
		"Better not tell you now",
		"Cannot predict now",
		"Concentrate and ask again",
		"Don't count on it",
		"My reply is no",
		"My sources say no",
		"Outlook not so good",
		"Very doubtful"
	};
	static Random rand = new Random();

	public static void main(String[] args) {
		Console c = System.console();
		
		while (prompt(c, "What's your question? ").length() > 0) {
			giveAnswer(c);
		}
		
		System.exit(0);
	}

	public static String prompt(Console c, String prompt) {
		c.printf("%s", prompt);
		return c.readLine();
	}
	
	public static void giveAnswer(Console c) {
		int index = rand.nextInt(answers.length);
		c.printf("%s\n", answers[index]);
	}
}
