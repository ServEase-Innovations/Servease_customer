# Step 1: Use a Maven base image to build the app
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory for Maven
WORKDIR /app

# Copy the pom.xml and install dependencies (cache this layer)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire source code and build the JAR
COPY src ./src
RUN mvn clean install

# Step 2: Use a slim OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR from the build step
COPY --from=build /app/target/customertab-0.0.1-SNAPSHOT.jar /app/customertab-0.0.1-SNAPSHOT.jar

# Expose the port Spring Boot will run on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "/app/customertab-0]
