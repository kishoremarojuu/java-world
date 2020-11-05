FROM openjdk:8-jdk-alpine
ADD target/spring_datajpa_rds.jar spring_datajpa_rds.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "spring_datajpa_rds.jar"]