FROM openjdk:17
EXPOSE 8081
ADD target/demacia-docker.jar demacia-docker.jar
ENTRYPOINT ["java", "-jar", "/demacia-docker.jar"]