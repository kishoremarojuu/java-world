package project1_java8_stream_api.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project1_java8_stream_api.models.Customer;

import java.util.List;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {

	List<Customer> findAll();
}
