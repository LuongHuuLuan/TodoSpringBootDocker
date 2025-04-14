# Stage 1: Build WAR
FROM maven:3.8.5-openjdk-11-slim AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run WAR
FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/TodoApp-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java", "-jar", "app.war"]