package project1_java8_stream_api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Integer tier;
	
}

//use full queries
//SELECT * FROM CUSTOMER C INNER JOIN PRODUCT_ORDER PR WHERE C.ID=PR.CUSTOMER_ID
//