FROM registry.access.redhat.com/openjdk/openjdk-11-rhel8:latest
MAINTAINER josue.solorio@softtek.com
ADD target/benchmarkJavaMaven-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080:8080
ENTRYPOINT ["java","-jar","app.jar"]
