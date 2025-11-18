#FROM eclipse-temurin:11-jdk
#WORKDIR /app
#COPY target/*.jar app.jar
#CMD ["java", "-jar","app.jar"]

#FROM maven:3.9.4-eclipse-temurin-11 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests

# Étape 2 : Exécution avec JDK 11
#FROM eclipse-temurin:11-jdk
#WORKDIR /app
#COPY --from=build /app/target/*.jar app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]