# Build & Test Stage
FROM maven:3.9.16-eclipse-temurin-17 AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package

# Runtime Stage
FROM eclipse-temurin:17-jre-noble
RUN useradd --create-home --shell /bin/bash appuser
WORKDIR /app
COPY --from=builder /build/target/*.jar ./app.jar
EXPOSE 8080
USER appuser
ENTRYPOINT ["java", "-jar", "app.jar"]
