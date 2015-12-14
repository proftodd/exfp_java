package com.exfp.ipo;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Quoter {
	
	public static class Quote {
		public String source;
		public String quote;
	}

	public static void main(String [] args) {
		Console c = System.console();
		List<Quote> quoteList = new ArrayList<Quote>();
		String quote;
		
		do {
			quote = getQuote(c, quoteList);
		} while (!quote.equalsIgnoreCase("X"));
		
		quoteList.stream().forEach(
			aQuote -> printQuote(aQuote)
		);
		
		System.exit(0);
	}
	
	public static String getQuote(Console c, List<Quote> quoteList) {
		System.out.print("Give me a quote (X to exit): ");
		String quote = c.readLine();
		if (!quote.equalsIgnoreCase("X")) {
			System.out.print("Who said it? ");
			String source = c.readLine();
			Quote aQuote = new Quote();
			aQuote.source = source;
			aQuote.quote = quote;
			quoteList.add(aQuote);
		}
		return quote;
	}
	
	public static void printQuote(Quote quote) {
		System.out.println(quote.source + " says, \"" + quote.quote + "\"");	
	}
}
