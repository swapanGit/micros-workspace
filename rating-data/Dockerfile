FROM openjdk:14
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8803:8803
ENTRYPOINT ["java","-jar","/app.jar"]