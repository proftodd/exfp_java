package com.exfp.struct;

import java.io.Console;
import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FilterRecords {
	
	public static enum QueryChoice {
		NO_FILTER, NAME, POSITION, SEPARATION_DATE;
		
		public static QueryChoice fromInt(int choice) {
			switch (choice) {
				case 1:  return NAME;
				case 2:  return POSITION;
				case 3:  return SEPARATION_DATE;
				default: return NO_FILTER;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Console c = System.console();
		List<Employee> staff = Employee.getStaff();
		
		SimpleEntry<QueryChoice, Object> query = getSearchQuery(c);
		switch(query.getKey()) {
			case NAME:
				staff = staff
					  .stream()
			          .filter(emp -> emp.fullName().toLowerCase().contains(query.getValue().toString().toLowerCase()))
			          .collect(Collectors.toList());
				break;
			case POSITION:
				staff = staff
				      .stream()
				      .filter(emp -> emp.position.toLowerCase().contains(query.getValue().toString().toLowerCase()))
				      .collect(Collectors.toList());
				break;
			case SEPARATION_DATE:
				staff = staff
				      .stream()
				      .filter(emp -> emp.separationDate.compareTo((Date) query.getValue()) < 0)
				      .collect(Collectors.toList());
				break;
			case NO_FILTER:
				break;
		}
				
		Employee.printStaffList(c, staff);
		
		System.exit(0);
	}

	private static SimpleEntry<QueryChoice, Object> getSearchQuery(Console c) {
		c.printf("%s\n", "How do you want to search?");
		c.printf("%s\n", "1. By name");
		c.printf("%s\n", "2. By position");
		c.printf("%s\n", "3. By separation date");
		c.printf("%s", "Enter selection: ");
		try {
			Integer key = Integer.parseInt(c.readLine());
			if (key == 1) {
				c.printf("%s", "Enter search term: ");
				return new SimpleEntry<QueryChoice, Object>(QueryChoice.NAME, c.readLine());
			} else if (key == 2) {
				c.printf("%s", "Enter search term: ");
				return new SimpleEntry<QueryChoice, Object>(QueryChoice.POSITION, c.readLine());
			} else if (key == 3) {
				c.printf("%s", "Enter date: ");
				return new SimpleEntry<QueryChoice, Object>(QueryChoice.SEPARATION_DATE, Employee.DATE_FORMAT.parse(c.readLine()));
			} else {
				c.printf("%s\n\n", "Unrecognized choice, please try again");
				return getSearchQuery(c);
			}
		} catch (Exception e) {
			c.printf("%s\n", "Unrecognized choice, please try again");
			return getSearchQuery(c);
		}
	}
}
