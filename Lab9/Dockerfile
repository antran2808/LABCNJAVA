# Sử dụng hình ảnh chính thức của OpenJDK làm hình ảnh cha
FROM openjdk:21-slim

EXPOSE 8080 8000

ADD target/springboot-mysql-docker.jar springboot-mysql-docker.jar
ENTRYPOINT ["java","-jar","/springboot-mysql-docker.jar"]
