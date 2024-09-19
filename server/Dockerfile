FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/server-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8111

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

