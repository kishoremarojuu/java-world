package com.repo.java8.java_101_comparator;

import java.util.List;
import java.util.stream.Collectors;

public class TaxService {

	public static List<Employee> evaluateTaxUsers(String input) {

		return ("tax".equalsIgnoreCase(input)) ? DataBase.getEmployees().stream().filter(employee -> employee.getSalary() > 50000).collect(Collectors.toList()) :
				DataBase.getEmployees().stream().filter(employee -> employee.getSalary() < 50000).collect(Collectors.toList());


	}

	public static void main(String[] args) {
		System.out.println(evaluateTaxUsers("tax"));
	}
}
