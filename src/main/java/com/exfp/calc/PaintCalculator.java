package com.exfp.calc;

import java.io.Console;

public class PaintCalculator {

	public static final double SQFT_PER_GALLON = 350.0;
	
	public static void main(String [] args) {
		Console c = System.console();
		
		int roomShape = getRoomShape(c);
		while (roomShape != 1 && roomShape != 2 && roomShape != 3) {
			System.out.println("Selection not recognized");
			roomShape = getRoomShape(c);
		}
		
		double area = 0.0;
		switch(roomShape) {
			case 1:
				area = getRectangularArea(c);
				break;
			case 2:
				area = getEllipticalArea(c);
				break;
			case 3:
				area = getLArea(c);
				break;
			default:
				System.err.println("Not recognized");
		}
		
		int gallons = getGallonsForArea(area);
		
		System.out.printf("You will need to purchase %d gallons of paint to cover %.2f square feet.\n", gallons, area);
		
		System.exit(0);
	}
	
	public static int getRoomShape(Console c) {
		System.out.println("What shape ceiling do you need to paint?");
		System.out.println("1. Rectangle");
		System.out.println("2. Oval");
		System.out.println("3. L-shaped");
		System.out.print("Selection: ");
		String selection = c.readLine();
		try {
			return Integer.parseInt(selection);
		} catch(NumberFormatException e) {
			System.out.println("Selection not recognized: " + selection);
			return getRoomShape(c);
		}
	}
	
	public static double getRectangularArea(Console c) {
		System.out.print("Width: ");
		Double width = Double.parseDouble(c.readLine());
		System.out.print("Height: ");
		Double height = Double.parseDouble(c.readLine());
		return height * width;	
	}
	
	public static double getEllipticalArea(Console c) {
		System.out.print("Major axis: ");
		Double major = Double.parseDouble(c.readLine()) / 2.0;
		System.out.print("Minor axis: ");
		Double minor = Double.parseDouble(c.readLine()) / 2.0;
		return Math.PI * major * minor;	
	}
	
	public static double getLArea(Console c) {
		System.out.println("For the larger part of the L:");
		System.out.print("Width: ");
		Double w1 = Double.parseDouble(c.readLine());
		System.out.print("Height: ");
		Double h1 = Double.parseDouble(c.readLine());
		System.out.println("For the smaller part of the L:");
		System.out.print("Width: ");
		Double w2 = Double.parseDouble(c.readLine());
		System.out.print("Height: ");
		Double h2 = Double.parseDouble(c.readLine());
		return w1 * h1 + w2 * h2;
	}
	
	public static int getGallonsForArea(double area) {
		return (int) Math.ceil(area / SQFT_PER_GALLON);	
	}
}
