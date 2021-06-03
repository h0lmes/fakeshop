FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/fakeshop-0.0.1.jar /deployments/fakeshop-0.0.1.jar
CMD ["java", "-jar", "/deployments/fakeshop-0.0.1.jar"]