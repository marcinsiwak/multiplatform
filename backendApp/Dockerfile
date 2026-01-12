FROM eclipse-temurin:21-jdk
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/libs/*-all.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]