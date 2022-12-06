package project1_java8_stream_api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project1_java8_stream_api.models.Customer;
import project1_java8_stream_api.models.Product;
import project1_java8_stream_api.repos.CustomerRepo;
import project1_java8_stream_api.repos.ProductRepo;

import java.util.List;

@RestController
@RequestMapping("/")
public class SampleController {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;


    @GetMapping("getAllCustomers")
    public void getMethod(){
        List<Customer> all = customerRepo.findAll();

        all.stream().forEach(System.out::println);



    }

    @GetMapping("getAllProducts")
    public void getAllProducts(){
        List<Product> productList = productRepo.findAll();

        productList.stream().forEach(System.out::println);



    }
}
