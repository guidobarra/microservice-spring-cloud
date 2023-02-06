FROM maven:3.8.7-amazoncorretto-11 as build
ARG PROJECT_NAME='PROJECT'

WORKDIR /workspace

COPY . /workspace/

RUN mvn clean package

FROM openjdk:11.0.16-jre-slim as prod
ARG PROJECT_NAME='PROJECT'
ARG PORT=9090


EXPOSE ${PORT}/tcp

VOLUME /opt/logs

COPY --from=build /workspace/${PROJECT_NAME}/target/*.jar /opt/app.jar

COPY ${PROJECT_NAME}/src/main/resources/* /opt/

WORKDIR /opt

CMD ["-Xmx1000m", "-Xss512m", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/opt/logs"]

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=beta", "/opt/app.jar"]