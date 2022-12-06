package tutorial_100_java8.java_100_consumer_predicate_supplier;

import java.util.Arrays;
import java.util.List;

public class ConsumerDemo {

	public static void main(String[] args) {
		/*
		 * Consumer<Integer> consumer = t -> System.out.println("Printing  : " + t);
		 * 
		 * consumer.accept(10);
		 */

		List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);

		list1.stream().forEach(t -> System.out.println("print  : " + t));

	}
}
