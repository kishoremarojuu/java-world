package tutorial_100_java8.java_100_consumer_predicate_supplier;

import java.util.Arrays;
import java.util.List;

public class SupplierDemo {

	public static void main(String[] args) {


		List<String> list1 = Arrays.asList();

		System.out.println(list1.stream().findAny().orElse("Hi viewers"));


	}
}
