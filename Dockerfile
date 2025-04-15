# BUILDER
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# FINAL IMAGE
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/inditex-test*.jar inditex-test.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "inditex-test.jar"]