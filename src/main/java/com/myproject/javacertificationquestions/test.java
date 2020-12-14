package com.myproject.javacertificationquestions;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 *
 */
public class test {
        public static void main(String[] args) {
            List<String> dryFruits = new ArrayList<>();
            dryFruits.add("Walnut");
            dryFruits.add("Apricot");
            dryFruits.add("Almond");
            dryFruits.add("Date");


            ListIterator<String> stringListIterator = dryFruits.listIterator();
            while(stringListIterator.hasNext()){
                System.out.println(stringListIterator.next());
            };




           /* Iterator<String> iterator = dryFruits.iterator();
            while(iterator.hasNext()) {
                String dryFruit = iterator.next();
                System.out.println(dryFruit);
                if(dryFruit.startsWith("A")) {
                    //dryFruits.remove(dryFruit);
                }
            }

            System.out.println(dryFruits);*/
        }

}
