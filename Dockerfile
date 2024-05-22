FROM openjdk:19-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} backend/app.jar

ENTRYPOINT ["java", "-jar", "backend/app.jar"]