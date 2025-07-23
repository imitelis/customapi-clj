# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the Uberjar into the container
COPY target/uberjar/server.jar /usr/src/app/server.jar

# Run the Clojure application
CMD ["java", "-jar", "server.jar"]