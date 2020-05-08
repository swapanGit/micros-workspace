FROM openjdk:14
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8802:8802
ENTRYPOINT ["java","-jar","/app.jar"]