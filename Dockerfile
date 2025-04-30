FROM openjdk:17
USER root
ENV TZ "Asia/Shanghai"
ENV LANG C.UTF-8
RUN mkdir -p /application/StudentManager/log
#工作目录
WORKDIR /application/StudentManager/
COPY /target/StudentManager-1.0.0.jar /application/StudentManager/app.jar
EXPOSE 8080
ENTRYPOINT["java","-jar","./app.jar"]