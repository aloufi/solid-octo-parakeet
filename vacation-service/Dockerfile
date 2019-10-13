FROM openjdk:8-jdk-alpine
ADD build/libs/Vacation-service-0.0.1-SNAPSHOT.jar vacationAPI.jar
ENTRYPOINT [ "java", "-jar", "vacationAPI.jar" ]
