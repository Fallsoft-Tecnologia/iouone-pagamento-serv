FROM openjdk:17
ADD target/iouone-0.0.1-SNAPSHOT.jar app.jar
# Definindo a variável de ambiente com o valor do argumento EXPOSE_PORT
ARG EXPOSE_PORT=8080
ENV PORT=${EXPOSE_PORT}

# Expondo a porta configurada
EXPOSE ${PORT}
ENTRYPOINT ["java", "-jar", "/app.jar"]