FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

RUN find /app/target -name "*.jar" -not -name "*sources.jar" -not -name "*javadoc.jar" -exec cp {} /app/application.jar \;

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/application.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]