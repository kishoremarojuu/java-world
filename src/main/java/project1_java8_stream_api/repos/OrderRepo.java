package project1_java8_stream_api.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project1_java8_stream_api.models.Order;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {

	List<Order> findAll();
}
