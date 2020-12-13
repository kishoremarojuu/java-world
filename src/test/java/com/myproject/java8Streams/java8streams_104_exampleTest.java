package com.myproject.java8Streams;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class java8streams_104_exampleTest {

    private List<Potus> potusList = Arrays.asList(
            new Potus("Donald", "Trump", 2016,"Republican"),
            new Potus("Barack", "Obama", 2012,"Democratic"),
            new Potus("Barack", "Obama", 2008,"Democratic"),
            new Potus("George", "Bush", 2004,"Republican"),
            new Potus("George", "Bush", 2000,"Republican")
    );

    @Test
    public void Testingsomethingg(){

        potusList.stream()
                .filter(potus -> potus.getParty().equals("Republican"))
                .forEach(new Consumer<Potus>() {
                    @Override
                    public void accept(Potus potus) {
                        System.out.println(potus);
                    }
                });
    }

    class Potus {
        private String firstName;
        private String lastName;
        private Integer electionYear;
        private String party;

        public Potus(String firstName, String lastName, Integer electionYear, String party) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.electionYear = electionYear;
            this.party = party;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Integer getElectionYear() {
            return electionYear;
        }

        public void setElectionYear(Integer electionYear) {
            this.electionYear = electionYear;
        }

        public String getParty() {
            return party;
        }

        public void setParty(String party) {
            this.party = party;
        }

        @Override
        public String toString() {
            return "Potus{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", electionYear=" + electionYear +
                    ", party='" + party + '\'' +
                    '}';
        }
    }

}