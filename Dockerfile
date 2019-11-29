FROM openjdk:11.0.5 AS builder

RUN apt update && apt-get install -y maven
COPY src /build/src
COPY pom.xml /build
WORKDIR /build
RUN mvn clean compile package

FROM openjdk:11.0.5

COPY --from=builder /build/target/kubernetes-client-test-1.0-SNAPSHOT-jar-with-dependencies.jar /java/kubernetes-client-test-fat.jar
CMD ["java", "-jar", "/java/kubernetes-client-test-fat.jar"]
