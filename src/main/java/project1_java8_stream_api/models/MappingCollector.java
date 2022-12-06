package project1_java8_stream_api.models;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MappingCollector {
    static List<Employee> employeeList
            = Arrays.asList(new Employee("Tom Jones", 45, 15000.00),
            new Employee("Harry Andrews", 45, 7000.00),
            new Employee("Ethan Hardy", 65, 8000.00),
            new Employee("Nancy Smith", 22, 10000.00),
            new Employee("Deborah Sprightly", 29, 9000.00));

    public static void main(String[] args) {
        List<String> employeeNames = employeeList.stream().collect(Collectors.mapping(employee -> employee.getName(), Collectors.toList()));
       System.out.println(employeeNames);
      System.out.println("_------------------------------");

      List<String> collect1 = employeeList
              .stream()
              .map(employee -> employee.getName())
              .collect(Collectors.toList());

      System.out.println(collect1);

      Map<String, List<Employee>> collect = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.toList()));

        //  System.out.println("List of employee names:" + employeeNames);

        //System.out.println(collect);
    }
}