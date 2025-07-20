# ----------- Build stage -------------
    FROM openjdk:21-jdk-slim AS builder

    # Set working dir
    WORKDIR /app
    
    # Copy project files
    COPY project.clj /app/
    COPY src /app/src
    COPY resources /app/resources
    
    # Optional: include a config file if needed at build time (e.g., test)
    # COPY config.edn /app/config.edn
    
    # Compile the uberjar
    RUN lein uberjar
    
    # ----------- Runtime stage -------------
    FROM openjdk:21-jdk-slim AS runner
    
    # Create a user for better security
    RUN useradd -m appuser
    
    WORKDIR /app
    
    # Copy only the built JAR from the builder stage
    COPY --from=builder /app/target/uberjar/customapi-0.1.0-standalone.jar /app/customapi.jar
    
    # Use non-root user
    USER appuser
    
    # Expose the server port
    EXPOSE 3000
    
    # Run the app
    CMD ["java", "-jar", "customapi.jar"]
    