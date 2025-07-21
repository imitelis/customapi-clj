# Use official Clojure image with Leiningen pre-installed
# ----------- Build stage -------------
FROM clojure AS build-stage

# Set working directory inside the container
WORKDIR /usr/src/app

# Copy project.clj into container
COPY project.clj .

# Pre-download dependencies
RUN lein deps

# Copy actual server files
COPY . .

# Default command to run your app - adjust if your main class or start differs
CMD ["lein", "uberjar"]

# Use an official OpenJDK runtime as a parent image
# ----------- Runner stage -------------
FROM openjdk:21-jdk-slim

# Set working directory inside the container
WORKDIR /usr/src/app

# Copy project files into container (adjust as needed)
COPY --from=build-stage /usr/src/app/target/uberjar/server.jar /usr/src/app 

# Default command to run your app - adjust if your main class or start differs
CMD ["java", "-jar", "/usr/src/app/server.jar"]