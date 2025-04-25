FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY target/real_test-1.0.0-SNAPSHOT-runner.jar /app/application.jar

EXPOSE 8080
EXPOSE 9000

CMD ["java", "-jar", "application.jar"]