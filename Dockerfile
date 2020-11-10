FROM openjdk:8-jdk-alpine
ADD target/devops_spring_example_two2.jar divyadocker.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "divyadocker.jar"]
