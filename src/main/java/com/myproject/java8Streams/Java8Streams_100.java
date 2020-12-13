package com.myproject.java8Streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Streams_100 {
    public static void main(String[] args) {

        List<Java8Streams_100_User> java8Streams100Users = Stream.of(
                new Java8Streams_100_User(100, "kishoremaorjuu", "testPassword1", "kishore@gmail.com"),
                new Java8Streams_100_User(101, "divyababoju", "testPassword1","divya@gmail.com"),
                new Java8Streams_100_User(102, "rojababoju", "testPassword1","roja@gmail.com"),
                new Java8Streams_100_User(103, "ashokamaroju", "testPassword1","ashokamaroju@gmail.com")
        ).collect(Collectors.toList());


        for (Java8Streams_100_User u : java8Streams100Users) {
            System.out.println(u.getUserName());
        }
    }
}
