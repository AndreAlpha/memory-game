FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:21-jre
WORKDIR /app

RUN apt-get update && apt-get install -y \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    libfreetype6 \
    x11-apps \
    && rm -rf /var/lib/apt/lists/*

COPY --from=build /app/target/memory-game-1.0-SNAPSHOT.jar app.jar

RUN groupadd -r memorygroup && useradd -r -g memorygroup memoryuser
USER memoryuser

ENTRYPOINT ["java", "-jar", "app.jar"]