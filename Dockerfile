# Use an official Maven image to build the project
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Use a lightweight Java runtime image for running the application
FROM eclipse-temurin:17-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/bmc.jar ./bmc.jar

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "bmc.jar"]
