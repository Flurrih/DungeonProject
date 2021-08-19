FROM openjdk:16-jdk-alpine
COPY /target/DungProj-1.0-SNAPSHOT.jar DungProj-1.0-SNAPSHOT.jar
COPY src/main/java/maps/ ./src/main/java/maps/
ENTRYPOINT ["java","-jar", "/DungProj-1.0-SNAPSHOT.jar"]