FROM openjdk:11-jdk-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
<<<<<<<< HEAD:back/vita-wearable/Dockerfile
EXPOSE 8083
========
EXPOSE 8081
>>>>>>>> origin/feat-back/apigateway:back/apigateway/Dockerfile
ENTRYPOINT ["java", "-jar", "/app.jar"]