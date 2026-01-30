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
ADD https://dtdg.co/latest-java-tracer /dd-java-agent.jar
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["sh", "-c", "java -javaagent:/dd-java-agent.jar -Ddd.service=$DD_SERVICE -Ddd.env=$DD_ENV -Ddd.version=$DD_VERSION -jar app.jar"]