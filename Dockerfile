# --- Stage 1: Build the application ---
# Use a Maven image that includes JDK 21 to build the JAR file
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project definition file
COPY pom.xml .

# Copy the rest of the source code
COPY src ./src

# Build the application, skipping tests for a faster CI build.
# The result will be a JAR file in the /app/target/ directory.
RUN mvn clean package -B -DskipTests

# --- Stage 2: Create the final, lightweight runtime image ---
# Use a minimal JRE image for the final container
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the executable JAR from the 'builder' stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# The command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
