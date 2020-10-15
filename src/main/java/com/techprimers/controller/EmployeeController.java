package com.techprimers.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class EmployeeController {


    @GetMapping("/employees")
    public ArrayList<Employee> readEmployees() {

        Employee employee1 = new Employee();
        System.out.println("adding first employee");
        employee1.setName("kishore"); employee1.setGender("male");
        employee1.setDateOfBirth("10/29/1990"); employee1.setAnnualSalary(25000); employee1.setCode("emp101");


        Employee employee3 = new Employee();
        employee3.setName("sreeja"); employee3.setGender("female");
        employee3.setDateOfBirth("06/06/1992"); employee3.setAnnualSalary(45000); employee3.setCode("emp103");

        Employee employee4 = new Employee();
        employee4.setName("sampath"); employee4.setGender("male");
        employee4.setDateOfBirth("01/01/1990"); employee4.setAnnualSalary(65000); employee4.setCode("emp104");

        Employee employee5 = new Employee();
        employee5.setName("bharathi"); employee5.setGender("female");
        employee5.setDateOfBirth("02/02/1990"); employee5.setAnnualSalary(75000); employee5.setCode("emp105");

        Employee employee6 = new Employee();
        employee6.setName("akhil"); employee6.setGender("male");
        employee6.setDateOfBirth("02/02/1993"); employee6.setAnnualSalary(75000); employee6.setCode("emp106");

        Employee employee7 = new Employee();
        employee7.setName("varsha"); employee7.setGender("female");
        employee7.setDateOfBirth("02/02/1993"); employee7.setAnnualSalary(75000); employee7.setCode("emp107");

        Employee employee8 = new Employee();
        employee7.setName("divyababoju"); employee7.setGender("female");
        employee7.setDateOfBirth("06/04/1991"); employee7.setAnnualSalary(50000); employee7.setCode("emp10");

        ArrayList<Employee> list = new ArrayList<Employee>();
        list.add(employee1);  list.add(employee3); list.add(employee4); list.add(employee5); list.add(employee6); list.add(employee7);
        list.add(employee8);


        return list;
    }

}
