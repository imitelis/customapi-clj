# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Uberjar into the container
COPY target/uberjar/customapi-0.1.0-standalone.jar /app/customapi.jar

# Expose the port your app will run on (default for your server is port 3000)
EXPOSE 3000

# Run the Clojure application
CMD ["java", "-jar", "customapi.jar"]
