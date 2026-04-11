FROM maven:3.9-eclipse-temurin-21 AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y

WORKDIR /pricefy

COPY . .

WORKDIR /pricefy/pricefy
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

EXPOSE 8080

WORKDIR /app

COPY --from=build /pricefy/pricefy/target/pricefy-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java" , "-jar" , "app.jar"]