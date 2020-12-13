package com.myproject.java8Streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class java8streams_102_mapperToInt {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("kishore", "divya", "roja", "ashoka", "karthik");

        //using lambda style
        List<User> userList = names.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return !s.equals("kishore");
                    }
                })
                .map(new Function<String, User>() {
                    @Override
                    public User apply(String s) {
                        User user = new User(s);
                        return user;
                    }
                })
                .collect(Collectors.toList());

        int sum = userList.stream()
                .mapToInt(new ToIntFunction<User>() {
                    @Override
                    public int applyAsInt(User value) {
                        return value.getAge();
                    }
                })
                .sum();
        System.out.println(sum);
    }

    static class User {
        private String name;
        private int age=30;

        public User() {
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

        public User(String name) {
            this.name = name;

        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
