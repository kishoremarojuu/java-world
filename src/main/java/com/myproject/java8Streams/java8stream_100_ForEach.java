package com.myproject.java8Streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class java8stream_100_ForEach {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("kishore", "divya", "roja", "ashoka", "karthik");

        //using imperative programming
        for (String n: names) {
            if(!n.equals("kishore")){
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

}
