FROM openjdk:17-jdk-buster as artifact
WORKDIR service

COPY build/libs/video-store-service.jar .

ARG VERSION=latest
ENV APPLICATION_VERSION=$VERSION

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "video-store-service.jar"]