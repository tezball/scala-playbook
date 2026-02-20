FROM sbtscala/scala-sbt:eclipse-temurin-21.0.8_9_1.12.3_3.3.7 AS build

WORKDIR /app

COPY project/build.properties project/plugins.sbt project/
COPY build.sbt .
RUN sbt update

COPY . .
RUN sbt stage

FROM eclipse-temurin:21-jre

RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY --from=build /app/target/universal/stage /app
COPY docker-entrypoint.sh /app/docker-entrypoint.sh

EXPOSE 9000

ENTRYPOINT ["/app/docker-entrypoint.sh"]
