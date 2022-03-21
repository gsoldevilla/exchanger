FROM openjdk:11

ADD target/exchanger-api-0.0.1-SNAPSHOT.jar exchanger-api-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "exchanger-api-0.0.1-SNAPSHOT.jar"]
