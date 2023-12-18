FROM maven:3.9-eclipse-temurin-21-alpine as build

WORKDIR /workspace/app

COPY council-rewritten-integration/pom.xml council-rewritten-integration/pom.xml
COPY council-rewritten-integration/src council-rewritten-integration/src

COPY council-common/pom.xml council-common/pom.xml
COPY council-common/src council-common/src

RUN mvn install -DskipTests -f council-common/pom.xml
RUN mvn install -DskipTests -f council-rewritten-integration/pom.xml
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../../council-rewritten-integration/target/*.jar)

FROM eclipse-temurin:21-jdk-alpine

VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","dev.jaczerob.council.integrations.rewritten.App"]