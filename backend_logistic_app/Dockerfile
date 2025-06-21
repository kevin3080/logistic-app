# Use a slim Java 17 image
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/backend_logistic_app-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]