# --- Stage 1: Build ---
# Usiamo un'immagine con Maven e Java 21 per compilare
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamo prima solo il pom.xml per scaricare le dipendenze (cache layer)
COPY pom.xml .
# Questo comando scarica i plugin e le librerie
RUN mvn dependency:go-offline

# Ora copiamo il codice sorgente
COPY src ./src

# Compiliamo e pacchettizziamo (crea il fat-jar)
RUN mvn clean package

# --- Stage 2: Runtime ---
# Usiamo un'immagine leggerissima (Alpine Linux) solo per eseguire
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiamo DAL primo stage (build) il file JAR creato
COPY --from=build /app/target/memory-game-1.0-SNAPSHOT.jar app.jar

# Creiamo un utente non-root per sicurezza (Best Practice Docker)
RUN addgroup -S memorygroup && adduser -S memoryuser -G memorygroup
USER memoryuser

# Comando che viene eseguito quando parte il container
ENTRYPOINT ["java", "-jar", "app.jar"]