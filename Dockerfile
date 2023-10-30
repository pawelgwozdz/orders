#base image with java 17
FROM openjdk:17-jdk-slim

#Maintener
MAINTAINER PawelGwozdz

#Copy app jar to docker image
COPY target/orders-0.0.1-SNAPSHOT.jar orders-0.0.1-SNAPSHOT.jar

#Executes app
ENTRYPOINT ["java", "-jar", "orders-0.0.1-SNAPSHOT.jar"]