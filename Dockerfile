FROM openjdk:21-jdk

ARG WAR_FILE=target/pdev-keycloak-service.war

COPY ${WAR_FILE} pdev-keycloak-service.war

ENTRYPOINT ["java", "-jar", "/pdev-keycloak-service.war"]

EXPOSE 8888