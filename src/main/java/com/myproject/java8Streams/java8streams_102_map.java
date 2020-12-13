package com.myproject.java8Streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class java8streams_102_map {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("kishore", "divya", "roja", "ashoka", "karthik");


        //using imperative style
        for(String n:names){
            if(!n.equals("Kishore")){
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

    static class User {
        private String name;

        public User() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
