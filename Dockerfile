# Use an OpenJDK 17 image as the base
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/tracking-service-0.0.1-SNAPSHOT.jar /app/tracking-service.jar

# Expose the port that Spring Boot will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/tracking-service.jar"]
