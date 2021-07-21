# Daily Code Snipets

### working java8_stream_api_exercise:
1. GitHub url:https://github.com/kishoremarojuu/project_java8_stream_api_exercise
This project will contain java8 stream api exercises most used on a daily basis
 ------------------------------------------------------------------------------------------------------------------------
### working with jackson library
https://dzone.com/articles/jackson-annotations-for-json-part-4-general 

-----------------------------------------------------------------------------------------------------------------------
### working with webclient: https://github.com/code-with-dilip/spring-webclient
https://github.com/kishoremarojuu/spring-webclient 
employee-app-starter  --> starter code, just to give hands on experience
employee-app --> main app, we will be consuming employee-service via webclient in reactive mode
employee-service-executable --> employee service jar 
employee-service --> main service (swagger+ h2 embedded Database)

-----------------------------------------------------------------------------------------------------------------------
### working with basic rest(get, post, put, patch, delete) with h2 database, response entity, path variable and others 
https://github.com/kishoremarojuu/spring-boot-h2-database-crud

-----------------------------------------------------------------------------------------------------------------------
### How to convert one object to other like POJO to DTO 
BeanUtils  --> import org.springframework.beans.BeanUtils 

-----------------------------------------------------------------------------------------------------------------------
### WOrking with date, pass the date string and get the date of type Date
   public static Date stringToDate(String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date d = formatter.parse(date);
        //System.out.println(date);
        return d;
    }
-----------------------------------------------------------------------------------------------------------------------
