FROM openjdk:8-alpine
RUN apk --no-cache add curl
COPY ./SOAPCUSTOMSERVER-DOCKER-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 7777
ENTRYPOINT ["java", "-cp", "SOAPCUSTOMSERVER-DOCKER-1.0-SNAPSHOT.jar", "it.sapienza.softeng.soapws.Server"]
