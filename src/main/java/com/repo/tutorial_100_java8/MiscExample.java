package com.repo.tutorial_100_java8;
//https://blog.devgenius.io/15-practical-exercises-help-you-master-java-stream-api-3f9c86b1cf82

import com.repo.tutorial_100_java8.java_101_comparator.Employee;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MiscExample {

    private List<Employee> employeeList;

    @BeforeEach
    public void setup(){
        employeeList = new ArrayList<>();
        employeeList.add(new Employee(100, "kishore", "dev", 8000));
        employeeList.add(new Employee(100, "akhil", "dev", 7000));
        employeeList.add(new Employee(101, "divya", "sre", 5000));
        employeeList.add(new Employee(103, "rob", "managment", 10000));
    }

    @Test
    public void returingListWithJobTitle(){
        Map<String, List<Employee>> stringListMap = usingJava7Way(employeeList);
        //System.out.println(stringListMap);
        Map<String, List<Employee>> listMap = usingJava8Way(employeeList);
        System.out.println(listMap);
    }
    Map<String, List<Employee>> usingJava7Way(List<Employee> employeeList){
        Map<String, List<Employee>> resultMap = new HashMap<>();
        for(int i=0;i<employeeList.size();i++){
            Employee employee=  employeeList.get(i);

            List<Employee> employeeSubList = resultMap.getOrDefault(employee.getDept(),new ArrayList<>());
            employeeSubList.add(employee);
            resultMap.put(employee.getDept(), employeeSubList);
        }
        return resultMap;
    }

    public Map<String, List<Employee>> usingJava8Way(List<Employee> employeeList){
        return employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getName));
    }

    @Test
    public void calculateAverageSalary(){
        int result =  calculateAverageSalaryUsingJava7(employeeList);
        System.out.println(result);
        Double result1= calculateAvergeSalaryUsingJava8(employeeList);
        System.out.println(result1);
    }

    public int  calculateAverageSalaryUsingJava7(List<Employee> employeeList){
        long sum =0; int count=0;

        for(int i=0;i< employeeList.size(); i++){
            Employee employee = employeeList.get(i);
            sum = sum+ employee.getSalary();
            count++;
        }
       return (int)sum/count;
    }
    public Double calculateAvergeSalaryUsingJava8(List<Employee> employeeList){
        return employeeList.stream().mapToLong(Employee::getSalary).average().getAsDouble();
    }

}
