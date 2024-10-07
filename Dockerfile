# Use an official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file to the container
COPY target/customertab-0.0.1-SNAPSHOT.jar /app/customertab-0.0.1-SNAPSHOT.jar

# Expose the port that your Spring Boot app will run on
EXPOSE 9090

# Run the Spring Boot app when the container starts
CMD ["java", "-jar", "/app/customertab-0.0.1-SNAPSHOT.jar"]
