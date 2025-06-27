# Use a Maven + JDK image
FROM maven:3.9.6-eclipse-temurin-21

# Set working directory
WORKDIR /app

# Copy the project files into the container
COPY . .

# Download dependencies and run tests
CMD ["mvn", "clean", "test"]
