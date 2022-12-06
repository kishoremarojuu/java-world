package tutorial_100_java8.java_103_flatMap;

import java.util.List;
import java.util.stream.Collectors;

////todo Getting the list of cities names which pincodes has char "3" or "5".


public class TestClass {
    public static void main(String[] args) {
        EmployeeFactory e = new EmployeeFactory();
        List<EmployeeFactory> employeeFactoryList = e.poulateEmployee();


        List<String> addressList = employeeFactoryList
                .stream()
                .flatMap(employeeFactory -> employeeFactory.getEmployeeList().stream()
                        .flatMap(employee -> employee.getAddresses().stream()
                                .filter(address -> address.getCity().equalsIgnoreCase("plano") && address.getPincode().equalsIgnoreCase("75075"))
                                .map(Address::getCity)))
                .collect(Collectors.toList());

        System.out.println(addressList);
    }


}
