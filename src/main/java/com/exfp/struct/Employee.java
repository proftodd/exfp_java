package com.exfp.struct;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Employee {
	public static final Date STILL_HERE = new Date(Long.MAX_VALUE);
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public String firstName;
	public String lastName;
	public String position;
	private String fullName;
	public Date separationDate;

	public String fullName() {
		if (fullName == null) {
			fullName = firstName + " " + lastName;
		}
		return fullName;
	}
	
	public static List<Employee> getStaff() throws FileNotFoundException, IOException, ParseException {
		List<Employee> staff = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader("staff.txt"));
		String line = reader.readLine();
		while((line = reader.readLine()) != null) {
			String [] fields = line.split("\t+", 4);
			Employee emp = new Employee();
			emp.firstName = fields[0];
			emp.lastName  = fields[1];
			emp.position  = fields[2];
			if (fields.length > 3) {
				emp.separationDate = DATE_FORMAT.parse(fields[3]);
			} else {
				emp.separationDate = STILL_HERE;
			}
			staff.add(emp);
		}
		reader.close();
		return staff;
	}
	
	public static void printStaffList(Console c, List<Employee> staff) {
		Supplier<Stream<Employee>> supplier = () -> staff.stream();
		int nameWidth = (int) Math.max(
				                    supplier.get()
				                            .map(emp -> emp.fullName().length())
				                            .collect(Collectors.maxBy(Integer::compare))
		                                    .orElse(0),
		                            "Name".length());
		int positionWidth = (int) Math.max(
									supplier.get()
				                            .map(emp -> emp.position.length())
				                            .collect(Collectors.maxBy(Integer::compare))
				                            .orElse(0),
				                    "Position".length());
		String formatString = "%-" + nameWidth + "s | %-" + positionWidth + "s | %-" + "Separation Date".length() + "s\n";
		
		c.printf("%s\n", "");
		c.printf(formatString, "Name", "Position", "Separation Date");
		c.printf("%s", String.format(formatString, '-', '-', '-').replaceAll(" ", "-"));
		supplier.get().forEach(emp -> 
			c.printf(formatString,
					 emp.fullName(),
					 emp.position,
					 (emp.separationDate == Employee.STILL_HERE) ? "" : Employee.DATE_FORMAT.format(emp.separationDate)
					)
		);
		c.printf("%s", String.format(formatString, '-', '-', '-').replaceAll(" ", "-"));
	}
}
