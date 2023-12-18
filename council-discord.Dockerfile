FROM maven:3.9-eclipse-temurin-21-alpine as build

WORKDIR /workspace/app

COPY council-discord/pom.xml council-discord/pom.xml
COPY council-discord/src council-discord/src

COPY council-common/pom.xml council-common/pom.xml
COPY council-common/src council-common/src

RUN mvn install -DskipTests -f council-common/pom.xml
RUN mvn install -DskipTests -f council-discord/pom.xml
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../../council-discord/target/*.jar)

FROM eclipse-temurin:21-jdk-alpine

VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","dev.jaczerob.council.discord.App"]