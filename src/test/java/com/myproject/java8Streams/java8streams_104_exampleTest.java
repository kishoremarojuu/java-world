package com.myproject.java8Streams;

import lombok.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class java8streams_104_exampleTest {

    private List<Potus> potusList = Arrays.asList(
            new Potus("Donald", "Trump", 2016,"Republican"),
            new Potus("Barack", "Obama", 2012,"Democratic"),
            new Potus("Barack", "Obama", 2008,"Democratic"),
            new Potus("George", "Bush", 2004,"Republican"),
            new Potus("George", "Bush", 2000,"Republican")
    );

    @Test
    public void printRepublicanPreseidentNames(){
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

}