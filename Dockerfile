FROM openjdk:20

EXPOSE 8080

COPY target/springDemo-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
