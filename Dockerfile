#
# Build Stage
#
FROM maven:3.6-openjdk-17-slim AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests
#
#
## Package Stage
#
FROM --platform=linux/x86_64 eclipse-temurin:17-jre-alpine
COPY --from=build /app/api/target/*.jar /app/app.jar
CMD ["java","-jar","/app/app.jar"]