# syntax=docker/dockerfile:1
FROM maven:3.8.7-openjdk-18-slim
WORKDIR /usr/src/spring-boot-backend/
COPY pom.xml .
COPY src ./src/
COPY ${PWD}/src/main/resources/application-containerization.properties ./src/main/resources/application.properties
RUN mvn package -DskipTests
CMD ["java", "-jar", "target/spring-boot-backend-0.0.1-SNAPSHOT.jar"]