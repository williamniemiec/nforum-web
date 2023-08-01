#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/dependency/webapp-runner.jar /usr/local/lib/nforum.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dserver.port=8080","-Dspring.profiles.active=prod", "-jar","/usr/local/lib/nforum.jar", "target/*.war"]
