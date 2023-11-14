# Utilisez l'image OpenJDK 11 depuis Docker Hub
FROM openjdk:8-jdk-alpine

# Exposez le port sur lequel votre application Spring Boot écoute (par exemple, le port 8080)
EXPOSE 8089

# Copiez le fichier JAR de votre application dans le conteneur
COPY target/gestion-station-ski-1.0.jar gestion-station-ski.jar

# Démarrez l'application Spring Boot
ENTRYPOINT ["java", "-jar", "gestion-station-ski.jar"]
