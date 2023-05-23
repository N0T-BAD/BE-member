FROM openjdk:17-jdk-slim
COPY build/libs/member-service-0.0.1-SNAPSHOT.jar member-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/member-service.jar"]
