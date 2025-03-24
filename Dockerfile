FROM openjdk:21
ARG JAR_FILE=./build/libs/wallet-service-recargapay-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
VOLUME /tmp
EXPOSE 8080