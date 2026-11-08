# build context: repository root (the api needs the mars-rover jar)
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /build

COPY pom.xml .
COPY src src
RUN mvn -B -q install -DskipTests

COPY api api
RUN mvn -B -q -f api/pom.xml package -DskipTests

FROM eclipse-temurin:11-jre-alpine
WORKDIR /app
COPY --from=build /build/api/target/rover-api.jar rover-api.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "rover-api.jar"]
