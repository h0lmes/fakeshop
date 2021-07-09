FROM public.ecr.aws/l8v7c0k7/jdk11:jdk11-jre-slim
COPY target/fakeshop-0.0.1.jar /deployments/fakeshop-0.0.1.jar
CMD ["java", "-jar", "/deployments/fakeshop-0.0.1.jar"]