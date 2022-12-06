package java8;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class java8ceritification {

    @Test
    public void testForLoop() {
        for (int i = 0; i < 5; i++, System.out.println(i + "HI")) ;
        //output:: weCanDeclareForLoopLikeThisAsWell
    }

    @Test //todo
    public void testForLoop1() {
        L1:
        for (int i = 5, j = 0; i > 0; i--) {
            L2:
            for (; j < 5; j++) {
                System.out.println(i + "" + j + " ");
                if (j == 0) continue L2;
            }
        }
    }

    @Test
    public void testForLoopIteratingTwoways() {
        //two-dimensional arrays:
        int arr[][] = {{1, 3, 5}, {7, 8}};
        for (int j = 0; j < arr.length; j++) {
            int[] a = arr[j];
            for (int k = 0; k < a.length; k++) {
                int i = a[k];
                System.out.println(i);
            }
        }
        //three-dimensional arrays:
        int[][][] arr1 = {{{1, 3, 5}, {7, 8}, {4, 5, 6}}};
        for (int j = 0; j < arr1.length; j++) {
            int[][] a = arr1[j];
            for (int k = 0; k < a.length; k++) {
                int i[] = a[k];
                for (int l = 0; l < i.length; l++) {
                    int m = i[l];
                    System.out.println(m);
                }

            }
        }

    }

    @Test
    public void testLocalDate() {
        LocalDate date = LocalDate.parse("1947-08-14");
        LocalTime time = LocalTime.MAX;
        System.out.println(date.atTime(time));
    }

    @Test
    public void invalidDateInTheCalendar() {
        LocalDate date = LocalDate.of(2020, 9, 30);
        System.out.println(date);


    }

    @Test
    public void testLocalDate1() {
        LocalDateTime localDate = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localDate.getSecond());
        System.out.println(localDate.getMinute());
        System.out.println(localDate.getHour());
    }

    @Test
    public void testLocalDate2() {
        List<LocalDate> localDates = new ArrayList<>();
        localDates.add(LocalDate.parse("2020-09-11"));
        localDates.add(LocalDate.parse("1919-02-25"));
        localDates.add(LocalDate.of(2020, 9, 8));
        localDates.add(LocalDate.of(1980, Month.DECEMBER, 31));

        localDates.removeIf(localDate -> localDate.getYear() < 2000);

        System.out.println(localDates);
    }

    @Test
    public void testLocalDate3() {
        LocalDate date = LocalDate.of(2012, 1, 11);
        Period period = Period.ofMonths(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy");
        System.out.print(formatter.format(date.minus(period)));
    }

    @Test
    public void testLocalDate4() {
        LocalTime time = LocalTime.of(16, 40);
        String amPm = time.getHour() >= 12 ? time.getHour() == 12 ? "PM" : "AM" : "kis";
        System.out.println(amPm);
    }

    @Test
    public void testLocalDate5() {
        LocalDate localDate = LocalDate.of(2012, 1, 11);
        Period period = Period.ofMonths(2);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("mm-dd-yy");
        System.out.println(dateTimeFormatter.format(localDate.minus(period)));
    }


    @Test
    public void testLocalDate6() {
      //  LocalDate localDate = LocalDate.parse("2020-5-9");
        LocalDate localDate = LocalDate.parse("2020-05-09");
        System.out.println(localDate);
        //output:: LocalDate.parse(CharSequence) method accepts String in "9999-99-99" format only. Single digit month and day value are padded with 0 to convert it to 2 digits.
        //
        //To represent 9th June 2018, format String must be "2018-06-09".
        //
        //If correct format string is not passed then an instance of java.time.format.DateTimeParseException is thrown.
    }

    @Test
    public void tesLocalDate7() {
        LocalDate date = LocalDate.parse("1980-03-16");
        System.out.println(date.minusYears(-5));
    }

    @Test
    public void tesLocalDate8() {
        LocalDate d1 = LocalDate.parse("1999-09-09");
        LocalDate d2 = LocalDate.parse("1999-09-09");
        LocalDate d3 = LocalDate.of(1999, 9, 9);
        LocalDate d4 = LocalDate.of(1999, 9, 9);
        System.out.println((d1 == d2) + ":" + (d2 == d3) + ":" + (d3 == d4));
        //ouput:: "parse" and "of" methods create new instances, so in this case you get 4 different instance of LocalDate stored at 4 different memory addresses.
    }

    @Test
    public void testLocalDate9() {
        LocalDate date = LocalDate.parse("2000-06-25");
        while (date.getDayOfMonth() >= 20) {
            System.out.println(date);
            date.plusDays(-1);

        }
        //date.plusDays(-1); creates a new LocalDate object {2000-06-24} but date reference variable still refers to {2000-06-25}. date.getDayOfMonth() again returns 25, this is an infinite loop.
    }

    @Test
    public void testLocalDate10() {
        LocalDate localDate = LocalDate.of(2020, 12, 15);
        int days = localDate.lengthOfMonth();
        System.out.println(days);
    }

    @Test
    public void testLocalDate11() {
        LocalDate date1 = LocalDate.parse("1980-03-16");
        LocalDate date2 = LocalDate.parse("1980-03-16");
        System.out.println(date1.equals(date2) + " : " + date1.isEqual(date2));
    }

    @Test
    public void testLocalDate_period() {
        Period period = Period.of(2020, 10, 29);
        System.out.println(period);

        Period period1 = Period.of(0, 0, 0);
        System.out.println(period1);
    }

    @Test
    public void testLocalDate_different_formats() {
        LocalDate date = LocalDate.of(1987, 11, 29);
        LocalTime time = LocalTime.of(8, 7, 5);
        String format = LocalDateTime.of(date, time).format(DateTimeFormatter.ISO_DATE_TIME);
        String format1 = LocalDateTime.of(date, time).format(DateTimeFormatter.ISO_TIME);
        System.out.println("Date is: " + format);
        System.out.println("Date is: " + format1);
    }

    @Test
    public void arrayIsHomogenous_arrayListIsHeterogenous() {
        List<String> list = new ArrayList<>(5); //add more elements, np, list will automatically increment

        list.add(0, "Array");
        list.add(1, "one");
        list.add(2, "List");
        list.add(3, "three");
        list.add(4, "four");
        list.add(5, "fivew");

        // System.out.println(list);

        int[] arrays = new int[2]; //increase the size, sout witll throw indexOutOfBoundsException
        arrays[0] = 1;
        arrays[1] = 2;

        for (int i = 0; i < arrays.length; i++) {
            System.out.println(i);
        }

    }

    @Test
    public void testArrayListAddition() {
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("D");

        List<String> list2 = new ArrayList<>();
        list2.add("B");
        list2.add("C");

        list1.addAll(1, list2);

        System.out.println(list1);
    }

    @Test
    public void testArrayListAddition1() {
        List<Character> list = new ArrayList<>();
        list.add(0, 'V');
        list.add('T');
        list.add(1, 'E');
        list.add(3, 'O');

        if (list.contains('O')) {
            list.remove('O');
        }

        for (char ch : list) {
            System.out.print(ch);
        }
        //output:: list.add(0, 'V'); => char 'V' is converted to Character object and stored as the first element in the list. list --> [V].
        //
        //list.add('T'); => char 'T' is auto-boxed to Character object and stored at the end of the list. list --> [V,T].
        //
        //list.add(1, 'E'); => char 'E' is auto-boxed to Character object and inserted at index 1 of the list, this shifts T to the right. list --> [V,E,T].
        //
        //list.add(3, 'O'); => char 'O' is auto-boxed to Character object and added at index 3 of the list. list --> [V,E,T,O].
        //
        //list.contains('O') => char 'O' is auto-boxed to Character object and as Character class overrides equals(String) method this expression returns true. Control goes inside if-block and executes: list.remove('O');.
        //
        //
        //remove method is overloaded: remove(int) and remove(Object). char can be easily assigned to int so compiler tags remove(int) method. list.remove(<ASCCI value of 'O'>); ASCCI value of 'A' is 65 (this everybody knows) so ASCII value of 'O' will be more than 65.
        //
        //
        //list.remove('O') throws runtime exception, as it tries to remove an item from the index greater than 65 but allowed index is 0 to 3 only.
    }

    @Test
    public void testArrayList100() {
        List<Integer> list = new ArrayList<Integer>();

        list.add(27);
        list.add(27);

        list.add(new Integer(27));
        list.add(new Integer(27));

        System.out.println(list.get(0) == list.get(1));
        System.out.println(list.get(2) == list.get(3));

        //ouput:: For 1st statement, list.add(27); => Auto-boxing creates an integer object for 27.
        //
        //For 2nd statement, list.add(27); => Java compiler finds that there is already an Integer object in the memory with value 27, so it uses the same object.
        //new Integer(27) creates a new Object in the memory, so System.out.println(list.get(2) == list.get(3)); returns false.


    }

    @Test
    public void testArrayList101() {
        List<String> list = new ArrayList<>();
        list.add(0, "Array");

        list.set(0, "List");

        System.out.println(list.toString());
    }

    @Test
    public void testArrayListRemoval() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("James", 25));
        students.add(new Student("James", 27));
        students.add(new Student("James", 25));
        students.add(new Student("James", 25));

        students.remove(new Student("James", 25));


        for (Student stud : students) {
            System.out.println(stud);
        }
    }

    class Student {
        private String name;
        private int age;

        Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return "Student[" + name + ", " + age + "]";
        }
        //output: Before you answer this, you must know that there are 5 different Student object created in the memory (4 at the time of adding to the list and 1 at the time of removing from the list). This means these 5 Student objects will be stored at different memory addresses.
    }

    @Test
    public void testArrayListRmoval() {
        List<Student4> students = new ArrayList<>();
        students.add(new Student4("James", 25));
        students.add(new Student4("James", 27));
        students.add(new Student4("James", 25));
        students.add(new Student4("James", 25));

        students.remove(new Student4("James", 25));

        for (Student4 stud : students) {
            System.out.println(stud);
        }
        //ouput:: remove(Object) method removes the first occurrence of matching object and equals(Object) method decides whether 2 objects are equal or not. equals(Object) method has been overridden by the Student class and equates the object based on their name and age.

    }

    class Student4 {
        private String name;
        private int age;

        Student4(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return "Student[" + name + ", " + age + "]";
        }

        public boolean equals(Object obj) {
            if (obj instanceof Student4) {
                Student4 stud = (Student4) obj;
                if (this.name.equals(stud.name) && this.age == stud.age) {
                    return true;
                }
            }
            return false;
        }
    }

    @Test
    public void testArrayListRemoval1() {
        List<String> list = new ArrayList<>();
        list.add("X");
        list.add("Y");
        list.add("X");
        list.add("Y");
        list.add("Z");
        list.remove(new String("Y"));
        System.out.println(list);
        //ouput:: After all the add statements are executed, list contains: [X, Y, X, Y, Z].
        //
        //list.remove(new String("Y")); removes the first occurrence of "Y" from the list, which means the 2nd element of the list. After removal list contains: [X, X, Y, Z].
        //
        //NOTE: String class and all the wrapper classes override equals(Object) method, hence at the time of removal when another instance is passes [new String("Y")], there is no issue in removing the  matching item.
    }

    @Test
    public void testArrayListRemoval2() {
        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(200);
        list.add(100);
        list.add(100);

        list.remove(new Integer(100));
        System.out.println(list);
    }

    @Test
    public void testArrayListRemoval3() {
        List<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("orange");
        fruits.add("grape");
        fruits.add("mango");
        fruits.add("banana");
        fruits.add("grape");

        if (fruits.remove("grape")) {
            fruits.remove("kishore");
        }
        System.out.println(fruits);
    }

    @Test
    public void testArrayListRemoval4(){
        List<String> dryFruits = new ArrayList<>();
        dryFruits.add("Walnut");
        dryFruits.add("Apricot");
        dryFruits.add("Almond");
        dryFruits.add("Date");

        for(String dryFruit : dryFruits) {
            if(dryFruit.startsWith("A")) {
                dryFruits.remove(dryFruit);
            }
        }

        System.out.println(dryFruits);  //output: An exception is thrown at run time
    }

    @Test
    public void intToDoubleTesting() {
        //   double [] arr = new int[2]; //Line 3
        //     System.out.println(arr[0]); //L
    }


    @Test
    public void javaByValueOrPassByReference() {
        Message obj = new Message();
        obj.print();
        change(obj);
        obj.print();
    }

    class Message {
        String msg = "Happy New Year!";

        public void print() {
            System.out.println(msg);
        }
    }

    public void change(Message m) {
        m = new Message(); //here we are creating new object, so m.msg hoids in new referene, not the old one
        m.msg = "Happy Holidays!";
    }

    @Test
    public void removingElementInTheList() {
        List<Integer> list = new ArrayList<>();
        list.add(new Integer(2));
        list.add(new Integer(1));
        list.add(new Integer(1));
        list.add(new Integer(1));
        list.add(new Integer(0));

        list.remove(list.indexOf(1));
             list.remove(2);

        System.out.println(list);
    }

    @Test
    public void remoeveElementsFromTheList() {
        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(200);
        list.add(100);
        list.add(200);
        list.remove(100);

        System.out.println(list);

        //output::List cannot accept primitives, it can accept objects only. So, when 100 and 200 are added to the list, then auto-boxing feature converts these to wrapper objects of Integer type.
        //
        //So, 4 items gets added to the list. One can expect the same behavior with remove method as well that 100 will be auto-boxed to Integer object.
        //
        //But remove method is overloaded in List interface: remove(int) => Removes the element from the specified position in this list
        //
        //and remove(Object)  => Removes the first occurrence of the specified element from the list.
        //
        //As remove(int) version is available, which perfectly matches with the call remove(100); hence compiler does not do auto-boxing in this case.
        //
        //But at runtime remove(100) tries to remove the element at 100th index and this throws IndexOutOfBoundsException.
    }

    @Test
    public void testJava8Predicate1_other_way_of_writing_predicate() {
        Predicate predicate = s -> true;
        Predicate predicate1 = s -> {
            return true;
        };
    }

    @Test
    public void testJava8Predicate() {
        String[] arr = {"A", "ab", "bab", "Aa", "bb", "baba", "aba", "Abab"};

        Predicate<String> p = s -> s.toUpperCase().substring(0, 1).equals("A");

        processStringArray(arr, p);
    }
    private static void processStringArray(String[] arr, Predicate<String> predicate) {
        for (String str : arr) {
            if (predicate.test(str)) {
                System.out.println(str);
            }
        }
    }

    @Test
    public void testPrimitvesVsObjects() {
        int[] a1 = {2, 5, 8, 6, 9,};
        int[] a2 = {2, 5, 8, 6, 9,};
        Object[] a3 = {4, 4, 5, 5};
        Object[] a4 = {4, 4, 5, 5};


        System.out.println(Arrays.equals(a1, a2));
        System.out.println(Arrays.deepEquals(a3, a4));
    }

    @Test
    public void guessOutput100() {
        double price = 90000;
        String model;
        if (price > 100000) {
            model = "Tesla Model X";
        } else if (price <= 100000) {
            model = "Tesla Model S";
        }
        // System.out.println(model);

        //output: 90000 is assigned to variable 'price' but you can assign parameter value or call some method returning double value, such as:
        //'double price = currentTemp();'.
        //Usage of LOCAL variable, 'model' without initialization gives compilation error. Hence, System.out.println(model); gives compilation error.
    }

    @Test
    public void testDouble() {
        Double[] arr = new Double[2];
        //System.out.println(arr[0]);

        int[] arra1 = new int[2];
        System.out.println(arra1[0] + arra1[1]);
    }

    @Test
    public void guessOutput101() {
        int[] arr = {2, 1, 0};
        for (int j : arr) {
            System.out.println(j);
        }
    }

    @Test
    public void guessOutput102() {
        new B().print();
    }

    public class B extends A {
        private int i2 = 10;

        public void print() {
            A obj = new A();
            System.out.println(obj.i1);
            System.out.println(obj.i2);
            System.out.println(this.i2);
            System.out.println(super.i2);
        }
    }

    public class A {
        private int i1 = 1;
        protected int i2 = 2;
    }

    @Test
    public void guessOutput103() {
        byte var = 100;
        switch (var) {
            case 100:
                System.out.println("var is 100");
                break;
            //   case 200:
            // System.out.println("var is 200");
            // break;
            default:
                System.out.println("In default");
        }
    }
    //output: In this case, switch expression [switch (var)] is of byte type.
    //
    //byte range is from -128 to 127. But in case expression [case 200], 200 is outside byte range and hence compilation error.

    @Test
    public void guessOutput104() {
        //System.out.println("Output is: 10"  !=5);

        //output:: Binary plus (+) has got higher precedence than != operator. Let us group the expression.
        //
        //"Output is: " + 10 != 5
        //
        //= ("Output is: " + 10) != 5
        //
        //[!= is binary operator, so we have to evaluate the left side first. + operator behaves as concatenation operator.]
        //
        //= "Output is: 10" != 5
        //
        //Left side of above expression is String, and right side is int. But String can't be compared to int, hence compilation error.
    }

    //list of final classes in java
    //java.lang.String The wrapper classes for the primitive types:
    // java.lang.Integer
    //java.lang.Byte
    //java.lang.Character
    // java.lang.Short
    //java.lang.Boolean
    //java.lang.Long
    // java.lang.Double
    // java.lang.Float
    //  java.lang.StackTraceElement (used in building exception stacktraces)

    // Most of the enum classes
    //java.math.BigInteger
    // java.math.BigDecimal
    //java.io.File
    //java.awt.Font
    //java.awt.BasicStroke
    //java.awt.Color
    // java.awt.GradientPaint,
    //java.awt.LinearGradientPaint
    //java.awt.RadialGradientPaint,
    //java.awt.Cursor
    // java.util.Locale
    // java.util.UUID
    //java.util.Collections
    //java.net.URL
    //java.net.URI
    // java.net.Inet4Address
    //java.net.Inet6Address
    // java.net.InetSocketAddress

    //classes of java 8 time API
    //Clock
    //Duration
    //Instant
    //LocalDate
    //LocalDateTime
    //LocalTime
    //MonthDay
    //OffsetDateTime
    //OffSetTime
    //Period
    //Year
    //YearMonth
    //ZonedDateTime
    //ZoneId
    //ZoneOffset

    @Test
    public void testStringMethod() {
        String str1 = " ";
        boolean b1 = str1.isEmpty();
        System.out.println(b1);
        str1.trim();
        b1 = str1.isEmpty();
        System.out.println(b1);
    }

    @Test
    public void testStringMethod1() {
        String[] strings = {"N", "L", "n", "O", "S"};
        Arrays.sort(strings);

        for (String s : strings) {
            System.out.println(s);
        }
    }

    @Test
    public void testStrinMethod2() {
        String s1 = "java";
        s1.concat(" rules");
        System.out.println("s1 refers to " + s1);

        String s2 = "php";
        s2 = s2.concat(" rules");
        System.out.println("s2 refers to " + s2);

        //ouput:: string is immutabe ?
        //https://stackoverflow.com/questions/8798403/string-is-immutable-what-exactly-is-the-meaning
    }

    @Test
    public void testStringMethod3() {
        String javaworld = "JavaWorld";
        String java = "Java";
        String world = "World";
        java = java + world; //this value is caluculated at run time, mnot compile time ,so java==javaworld is true
        System.out.println(java==(javaworld));

        String pythonWorld = "PythonWorld"; //this value is at compile time only
        String kishoreWorld = "KishoreWorld"; //this value is at compile time only
        System.out.println(pythonWorld==kishoreWorld); ////hence the pythonWorld==kishoreWorld is true

        //ouput from udayan:Please note that Strings computed by concatenation at compile time, will be referred by String Pool during execution. Compile time String concatenation happens when both of the operands are compile time constants, such as literal, final variable etc.
        //
        //Whereas, Strings computed by concatenation at run time (if the resultant expression is not constant expression) are newly created and therefore distinct.
        //
        //
        //`java += world;` is same as `java = java + world;` and `java + world` is not a constant expression and hence is calculated at runtime and returns a non pool String object "JavaWorld", which is referred by variable 'java'.
        //
        //
        //On the other hand, variable 'javaworld' refers to String Pool object "JavaWorld". As both the variables 'java' and 'javaworld' refer to different String objects, hence `java == javaworld` returns false.
    }

    @Test
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder(100);
        System.out.println(sb.length() + ":" + sb.toString().length());
    }

    @Test
    public void testStringBuilder1() {
        StringBuilder sb = new StringBuilder(5);
        sb.append("0123456789");
        sb.delete(8, 1000);
        System.out.println(sb);
    }

    @Test
    public void testStringBuilder_String() {
        StringBuilder sb = new StringBuilder("Java");
        String s1 = sb.toString();
        String s2 = "Java";

        System.out.println(s1 == s2);
    }


    @Test
    public void testStringBuilder_String_one() {
        List<String> dryFruits = new ArrayList<>();
        dryFruits.add(new String("Walnut"));
        dryFruits.add(new String("Apricot"));
        dryFruits.add(new String("Almond"));
        dryFruits.add(new String("Date"));

        for (int i = 0; i < dryFruits.size(); i++) {
            if (i == 0) {
                dryFruits.remove(new String("Almond"));
            }
        }

        System.out.println(dryFruits);

    }

    @Test
    public void testStringBuilder_String_two() {
        List<StringBuilder> dryFruits = new ArrayList<>();
        dryFruits.add(new StringBuilder("Walnut"));
        dryFruits.add(new StringBuilder("Apricot"));
        dryFruits.add(new StringBuilder("Almond"));
        dryFruits.add(new StringBuilder("Date"));

        for (int i = 0; i < dryFruits.size(); i++) {
            if (i == 0) {
                dryFruits.remove(new StringBuilder("Almond"));
            }
        }

        System.out.println(dryFruits);

    }

    @Test
    public void testStringBuilder_100() {
        StringBuilder stringBuilder = new StringBuilder("IZO");
        stringBuilder.append("-808");

        System.out.println(stringBuilder.length());
        System.out.println(stringBuilder.capacity());
    }

    @Test
    public void classCastExceptionExample() {
        /*INSERT*/
        int[] arr = new int[]{100, 20, 36};
        arr[1] = 5;
        arr[2] = 10;
        System.out.println("[" + arr[0] + ", " + arr[2] + "]"); //Line n1
    }

    @Test
    public void guessTheOutput105() {
        Point p1 = new Point();
        p1.x = 10;
        p1.y = 20;
        Point p2 = new Point();
        p2.assign(p1.x, p1.y);
        System.out.println(p1.toString() + ";" + p2.toString());
    }

    class Point {
        int x;
        int y;

        void assign(int x, int y) {
            x = this.x;
            this.y = y;
        }

        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }

    @Test
    public void testSpecialStringMethod() {
        Object[] arr = new Object[4];
        for (int i = 1; i <= 3; i++) {
            switch (i) {
                case 1:
                    arr[i] = new String("Java");
                    break;
                case 2:
                    arr[i] = new StringBuilder("Java");
                    break;
                case 3:
                    arr[i] = new SpecialString("Java");
                    break;
            }
        }
        for (Object obj : arr) {
            System.out.println(obj);
        }
    }

    class SpecialString {
        String str;

        SpecialString(String str) {
            this.str = str;
        }

    }
    //output: Variable 'arr' refers to an Object array of size 4 and null is assigned to all 4 elements of this array.
    //
    //for-loop starts with i = 1, which means at 1st index String instance is stored, at 2nd index StringBuiler instance is stored and at 3rd index SpecialString instance is stored. null is stored at 0th index.
    //
    //So, first null will be printed on to the console.
    //
    //String and StringBuilder classes override toString() method, which prints the text stored in these classes. SpecialString class doesn't override toString() method and hence when instance of SpecialString is printed on to the console, you get: <fully qualified name of SpecialString class>@<hexadecimal representation of hashcode>.
    //
    //Therefore output will be:
    //
    //null
    //
    //Java
    //
    //Java
    //
    //<Some text containing @ symbol>

    @Test
    public void guessOutput106() {
        Student1 student1 = new Student1("James", 25);
        int marks = 25;
        review(student1, marks);
        System.out.println(marks + "-" + student1.marks);
    }

    private void review(Student1 student1, int marks) {
        marks = marks + 10;
        student1.marks += marks;
    }

    class Student1 {
        String name;
        int marks;

        Student1(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

    }


    @Test
    public void guessOutput107() {
        Student2 student2 = new Student2();
        System.out.println(student2.name + "::" + student2.age);
//output:: Methods can have same name as the class. Student() and Student(String, int) are methods and not constructors of the class, note the void return type of these methods.

    }

    class Student2 {
        String name;
        int age;

        void Student() {
            Student("James", 25);
        }

        void Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @Test
    public void guessOutput108() {
        int a = 100;
        System.out.println(-a++);
    }

    @Test
    public void guessOutput109() {
        Test1 obj = new Test1();
        System.out.println(">" + obj.var1);
        System.out.println(">" + obj.var2);
        System.out.println(">" + obj.var3);
    }

    public class Test1 {
        char var1;
        double var2;
        float var3;
    }


    @Test
    public void guessOutput110() {
        char c = 'Z';
        long l = 100_00l;
        int i = 9_2;
        float f = 2.02f;
        double d = 10_0.35d;
        l = c + i;
        f = c * l * i * f;
        f = l + i + c;
        i = (int) d;
        f = (long) d;

        System.out.println(i);
    }

    @Test
    public void guessOutput111() {
        System.out.println("Hello" + 1 + 2 + 3 + 4);
        //output:+ operator with String behaves as concatenation operator.
    }
    @Test
    public void guessOutput(){
        Boolean [] arr = new Boolean[2];
        System.out.println(arr[0] + ":" + arr[1]);

        boolean[] booleans = new boolean[5];
        System.out.println(booleans[0] + ":" + booleans[1]);

        Integer[] integers = new Integer[2];
        System.out.println(integers[0] + ":" + integers[1]);

        int[] integersList = new int[2];
        System.out.println(integersList[0] + ":" + integersList[1]);

    }

    @Test
    public void testOutput12() {
        int y = 5;

        if (y++ == 11 && true) { //though the control is not going into the loop, it checks for frst condition, y++-11, the value of y is now 6
            System.out.println(" I m in the if block");
        } else if (true || --y == 4) { //here the y values will even be checed as first condition is true, it will go into the loop, so y value will still be 6
            System.out.println(y);
            System.out.println("I am in the else if  block");
        } else {
            System.out.println("I am in the else block");
        }

        System.out.println("I am in the main block");


    }

    @Test
    public void dealingWithStaticMethod() {
        try {
            m1();
        }/*catch (ArithmeticException a){
            System.out.println("I am here");
        }*/ finally {
            System.out.println("A");
        }
    }

    private static void m1() {
        System.out.println(1 / 0);
    }

    @Test
    public void dealingWithStaticBlock() {
        System.out.println("entered test clas");
    }

    static {
        // System.out.println(1/0);
    }

    @Test
    public void operatorsPrecendence() {
        int a = 10, b = 5, c = 1, result;
        int aeroplane = a++;
        System.out.println(a);
        System.out.println(++c);
    }

    @Test
    public void operatorsPrecendence1() {
        int i = 5;
        if (i++ < 6) {
            System.out.println(i++);
            System.out.println(i);
        }
        int j = 16;
        ++j;
        System.out.println(j);
    }

    @Test
    public void testingConstructor() {
        Student3 student3 = new Student3();
        System.out.println(student3.name + ":" + student3.age);
    }

    public class Student3 {
        String name;
        int age;

        Student3() {
            //Student3("james",34);
            //A constructor can call another constructor by using this(...) and not the constructor name.
            //Hence Student("James", 25); causes compilation error.
            this("james", 34);
        }

        Student3(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @Test
    public void testSwitchStatement() {
        String[][] arr = {{"7", "6", "5"}, {"4", "3"}, {"2", "1"}};
        for (int i = 0; i < arr.length; i++) { //Line n1
            for (int j = 0; j < arr[i].length; j++) { //Line n2
                switch (arr[i][j]) { //Line n3
                    case "2":
                    case "4":
                    case "6":
                        break; //Line n4
                    default:
                        continue; //Line n5
                }
                System.out.print(arr[i][j]); //Line n6
            }
        }
    }

    @Test
    public void testSwitchStatemenet2() {
        int score = 60;
        switch (score) {
            default:
                System.out.println("Not a valid score");
         /*   case score < 70:
                System.out.println("Failed");
                break;
            case score >= 70:
                System.out.println("Passed");
                break;*/
        }
        //output: case values must evaluate to the same type / compatible type as the switch expression can use.
    }

    @Test
    public void testSwitchStatemenet3() {
        Boolean b = new Boolean("tRUe");
      /*  switch (b) {
            case true:
                System.out.println("ONE");
            case false:
                System.out.println("TWO");
            default:
                System.out.println("THREE");


        }*/
//output: switch can accept primitive types: byte, short, int, char; wrapper types: Byte, Short, Integer, Character; String and enums.
        //
        //switch(b) causes compilation failure as b is of Boolean type.
        //pro-tip: why to override and equals method in java
        //https://www.techiedelight.com/why-override-equals-and-hashcode-methods-java/
    }

    @Test
    public void testSwitchStatement4() {
        String s = "A";
        String c1 = "A";
        String c2 = "B";
        String c3 = "C";

        switch (s) {
            //case c1:
            //  System.out.println("we are good");
            //case c2:
            //  System.out.println("we are c2");
            //case c3:
            //  System.out.println("we are c3");
            default:
                System.out.println("we are default");
        }
        //output: constants can be used for cases not variables

    }

    static String out = "";

    @Test
    public void testSwitchStatement5() {

        int x = 5, y = 8;

        if (x++ == 5)
            out += "1";
        if (x != 6) {

        } else if (x > 9) {
            out += "2";
        } else if (y < 9) {
            out += "3";
        } else if (x == 6) {
            out += "4";
        } else {
            out += "5";
        }

        System.out.println(out);
    }

    @Test
    public void testExceptionHandlingScenario() {

        try {
            method();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("IO exception caught");
        } catch (IOException | NoSuchFieldException ioException) {
            System.out.println("we are good");
        }
    }

    private static void method() throws IOException, ClassCastException, NoSuchFieldException {
        throw new FileNotFoundException();
    }

    @Test
    public void testExceptionHandlingScenario1() {
        try {
            print();
        } catch (NullPointerException exception) {
            System.out.println("NullPointerException");
        }
    }

    public static void print() {
        try {
            throw new NullPointerException();
        } catch (ClassCastException classCastException) {
            System.out.println("ClassCastException");
        } finally {
            System.out.println(" I am in the final block");
        }

    }

    @Test
    public void testExceptionHandlingScenario2() {
        try {
            int x = Integer.parseInt("one");
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("IllegalArgumentException");
        }
        //ouput::: why not "NumberFormatException" , here we are using IllegalArgumentException, this is parent
        // of NumberFormatException
    }

    @Test
    public void testObjectMethod() {
        Object[] objects = {1, 4, 6, 8, 34};
        System.out.println(mysize(objects));

    }

    public int mysize(Object obj) {
        return ((Object[]) obj).length;

    }

    @Test
    public void java_8_interfaces() {
        Whiz.print();
        //since java-8, static & defauly methods are allowed in interfaces
    }

    public interface Whiz {
        public static void main(String[] args) {
            System.out.println("I am good");
        }

        public static void print() {
            System.out.println("I am doing goood");
        }
    }


}
