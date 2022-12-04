FROM openjdk:11-jdk-oracle
WORKDIR mc2
ADD target/mc2.jar app.jar
ENTRYPOINT java -jar app.jar