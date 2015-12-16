package com.exfp.struct;

import java.io.Console;
import java.util.Comparator;
import java.util.List;

public class SortRecords {
	
	public static enum SortChoice {
		NO_SORT, FIRST_NAME, LAST_NAME, POSITION, SEPARATION_DATE;
		
		public static SortChoice fromInt(int choice) {
			switch(choice) {
				case 1:  return FIRST_NAME;
				case 2:  return LAST_NAME;
				case 3:  return POSITION;
				case 4:  return SEPARATION_DATE;
				default: return NO_SORT;
			}
		}
	};
	
	public static void main(String[] args) throws Exception {
		Console c = System.console();
		List<Employee> staff = Employee.getStaff();
		
		switch(getSortOption(c)) {
			case NO_SORT:
				break;
			case FIRST_NAME:
				staff.sort(Comparator.comparing(emp -> emp.firstName));
				break;
			case LAST_NAME:
				staff.sort(Comparator.comparing(emp -> emp.lastName));
				break;
			case POSITION:
				staff.sort(Comparator.comparing(emp -> emp.position));
				break;
			case SEPARATION_DATE:
				staff.sort(Comparator.comparing(emp -> emp.separationDate));
				break;
		}
		
		Employee.printStaffList(c, staff);
		
		System.exit(0);
	}

	private static SortChoice getSortOption(Console c) {
		int sortOption = -1;
		prompt(c);
		sortOption = Integer.parseInt(c.readLine());
		while (sortOption < 0 || sortOption > 4) {
			c.printf("%s\n\n", "Invalid choice, try again");
			prompt(c);
			sortOption = Integer.parseInt(c.readLine());
		}
		return SortChoice.fromInt(sortOption);
	}

	private static void prompt(Console c) {
		c.printf("%s\n", "How do you want to sort the output?");
		c.printf("%s\n", "0. No sort");
		c.printf("%s\n", "1. First name");
		c.printf("%s\n", "2. Last name");
		c.printf("%s\n", "3. Position");
		c.printf("%s\n", "4. Separation date");
		c.printf("%s", "Choice: ");
	}
}
