package com.myproject.java8Streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class java8streams_103_flatMap {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("kishore", 29, Arrays.asList("1","2","3")),
                new User("divya", 28, Arrays.asList("4","5")),
                new User("roja", 26, Arrays.asList("6","7","8"))
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

    static class User {
        private String name;
        private int age=30;
        private List<String> phoneNumbers;


        public User() {
        }

        public User(String name, int age, List<String> phoneNumbers) {
            this.name = name;
            this.age = age;
            this.phoneNumbers = phoneNumbers;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<String> getPhoneNumbers() {
            return phoneNumbers;
        }

        public void setPhoneNumbers(List<String> phoneNumbers) {
            this.phoneNumbers = phoneNumbers;
        }
    }
}
