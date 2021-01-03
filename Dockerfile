FROM openjdk:14
ADD target/inzynierka-0.0.1-SNAPSHOT.jar inzynierka-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java" , "-jar", "inzynierka-0.0.1-SNAPSHOT.jar"]