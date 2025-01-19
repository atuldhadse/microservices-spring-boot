
#importing java runtime
FROM openjdk:21-jdk-slim

#who is maintaining the image
LABEL "org.opencontainers.image.authors"="atuldhadse01@gmail.com"

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]