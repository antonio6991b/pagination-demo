FROM openjdk:8u312-jdk
WORKDIR /app
COPY target/pagination-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "pagination-0.0.1-SNAPSHOT.jar"]