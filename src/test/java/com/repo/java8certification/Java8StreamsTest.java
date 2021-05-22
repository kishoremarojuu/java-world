package com.repo.java8certification;

import lombok.*;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8StreamsTest {

    @Test
    public void printRepublicanPreseidentNames() {
        List<Potus> potusList = Arrays.asList(
                new Potus("Donald", "Trump", 2016, "Republican"),
                new Potus("Barack", "Obama", 2012, "Democratic"),
                new Potus("Barack", "Obama", 2008, "Democratic"),
                new Potus("George", "Bush", 2004, "Republican"),
                new Potus("George", "Bush", 2000, "Republican")
        );

        potusList.stream()
                .filter(potus -> potus.getParty().equals("Republican"))
                .forEach(potus -> System.out.println(potus));
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    class Potus {
        private String firstName;
        private String lastName;
        private Integer electionYear;
        private String party;


    }

    @Test
    public void java8_forEachExample1() {

        List<String> names = Arrays.asList("kishore", "divya", "roja", "ashoka", "karthik");

        //using imperative programming
        for (String n : names) {
            if (!n.equals("kishore")) {
                System.out.println(n);
            }

        }

        //using functional programming
        //using annonymouse inner class
    /*    names.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return !s.equals("kishore");
                    }
                })
                .forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                      //  System.out.println(s);
                    }
                });*/

        //using lambda functions, annonymous functions can be auto replaced bv intelliJ idea suggestions
        names.stream()
                .filter(names1 -> !names1.equals("kishore"))
                .forEach(System.out::println);


    }

    @Test
    public void java8MapExample() {
        List<String> names = Arrays.asList("kishore", "divya", "roja", "ashoka", "karthik");

        //using imperative style
        for (String n : names) {
            if (!n.equals("Kishore")) {
                User user = new User(n);
                System.out.println(user);
            }
        }

        System.out.println("using functional style");

        //using functional programming style
        //using annonymous style
    /*    names.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return !s.equals("Kishore");
                    }
                })
                .map(new Function<String, Object>() {
                    @Override
                    public User apply(String s ) {
                        User user = new User(s);
                        return user;
                    }
                })
                .forEach(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) {
                        System.out.println(o);
                    }
                });*/

        //using lambda style
        names.stream()
                .filter(s -> !s.equals("Kishore"))
                .map((Function<String, Object>) User::new)
                .forEach(System.out::println);

        //if you want,we can collect into other list as well
        List<Object> kishore = names.stream()
                .filter(s -> !s.equals("Kishore"))
                .map((Function<String, Object>) User::new)
                .collect(Collectors.toList());

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    class User {
        private String name;

    }

    @Test
    public void java8StreamsMapperToIntExample() {

        List<String> names = Arrays.asList("kishore", "divya", "roja", "ashoka", "karthik");

        //using lambda style
        List<User1> userList = names.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return !s.equals("kishore");
                    }
                })
                .map(new Function<String, User1>() {
                    @Override
                    public User1 apply(String s) {
                        User1 user = new User1(s);
                        return user;
                    }
                })
                .collect(Collectors.toList());

        int sum = userList.stream()
                .mapToInt(new ToIntFunction<User1>() {
                    @Override
                    public int applyAsInt(User1 value) {
                        return value.getAge();
                    }
                })
                .sum();
        System.out.println(sum);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    class User1 {
        private String name;
        private int age = 30;

        public User1(String name) {
            this.name = name;

        }
    }

    @Test
    public void java8SteamsFlatMapExample() {

        List<User3> users = Arrays.asList(
                new User3("kishore", 29, Arrays.asList("1", "2", "3")),
                new User3("divya", 28, Arrays.asList("4", "5")),
                new User3("roja", 26, Arrays.asList("6", "7", "8"))
        );


        //using annonymous class
        Optional<Object> any = users.stream()
                .map(user -> user.getPhoneNumbers().stream())
                .flatMap(new Function<Stream<String>, Stream<?>>() {
                    @Override
                    public Stream<?> apply(Stream<String> stringStream) {
                        return stringStream.filter(s -> s.equals("5"));
                    }
                })
                .findAny();

        any.ifPresent(o -> System.out.println(o));


        //using lambda functions
        Optional<Object> any2 = users.stream()
                .map(user -> user.getPhoneNumbers().stream())
                .flatMap((Function<Stream<String>, Stream<?>>) stringStream -> stringStream.filter(s -> s.equals("5")))
                .findAny();

        any2.ifPresent(o -> System.out.println(o));

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    class User3 {
        private String name;
        private int age = 30;
        private List<String> phoneNumbers;
    }

    @Test
    public void java8FlatMapExample2(){
        List<Customer> customers = getAll();

        //List<Customer>  convert List<String> -> Data Transformation
        //mapping : customer -> customer.getEmail()
        //customer -> customer.getEmail()  one to one mapping
        List<String> emails = customers.stream()
                .map(customer -> customer.getEmail())
                .collect(Collectors.toList());
        System.out.println(emails);

//customer -> customer.getPhoneNumbers()  ->> one to many mapping
        //customer -> customer.getPhoneNumbers()  ->> one to many mapping
        List<List<String>> phoneNumbers = customers.
                stream().map(customer -> customer.getPhoneNumbers())
                .collect(Collectors.toList());
        System.out.println(phoneNumbers);

        //List<Customer>  convert List<String> -> Data Transformation
        //mapping : customer -> phone Numbers
        //customer -> customer.getPhoneNumbers()  ->> one to many mapping
        List<String> phones = customers.stream()
                .flatMap(customer -> customer.getPhoneNumbers().stream())
                .collect(Collectors.toList());
        System.out.println(phones);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    static
    class Customer{
        private int id;
        private String name;
        private String email;
        private List<String> phoneNumbers;
    }

    public static List<Customer> getAll() {
        return Stream.of(
                new Customer(101, "john", "john@gmail.com", Arrays.asList("397937955", "21654725")),
                new Customer(102, "smith", "smith@gmail.com", Arrays.asList("89563865", "2487238947")),
                new Customer(103, "peter", "peter@gmail.com", Arrays.asList("38946328654", "3286487236")),
                new Customer(104, "kely", "kely@gmail.com", Arrays.asList("389246829364", "948609467"))
        ).collect(Collectors.toList());
    }


}