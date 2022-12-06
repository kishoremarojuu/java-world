package tutorial_100_java8.java_103_flatMap;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeFactory {
    List<Employee> employeeList = new ArrayList<>();

    public List<EmployeeFactory> poulateEmployee() {

        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setName("kshoremaorju");

        Address address1 = new Address("line-1", "town-1", "sanantonio", "pincode-1");
        Address address2 = new Address("line-2", "town-2", "plano", "75075");

        List<Address> addressList1 = Arrays.asList(address1, address2);
        employee1.setAddresses(addressList1);

        Employee employeee2 = new Employee();
        employeee2.setId(2);
        employeee2.setName("divyababoju");

        Address address3 = new Address("line-1", "town-1", "plano", "75075");
        Address address4 = new Address("line-2", "town-2", "plano", "pincode-4");

        List<Address> addressList2 = Arrays.asList(address3, address4);
        employeee2.setAddresses(addressList2);

        employeeList.add(employee1);
        employeeList.add(employeee2);


        EmployeeFactory employeeFactory = new EmployeeFactory();
        employeeFactory.setEmployeeList(employeeList);

        List<EmployeeFactory> employeeFactoryList = new ArrayList<>();
        employeeFactoryList.add(employeeFactory);

        return employeeFactoryList;


    }


}
