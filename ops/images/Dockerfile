# Stage 1: Build

FROM gradle:8.12.1-jdk21-corretto AS build
WORKDIR /multipart-application
COPY ../.. .
RUN gradle build --no-daemon

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /multipart-application
COPY --from=build /multipart-application/application/build/libs/application-1.0.0.jar multipart-application.jar
EXPOSE 8080
CMD ["java", "-jar", "multipart-application.jar"]