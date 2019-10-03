FROM openjdk:8-jdk-alpine
ADD build/libs/config-service-0.0.1-SNAPSHOT.jar config-server.jar
ENTRYPOINT [ "java", "-jar", "config-server.jar" ]
