#
# Build stage
#
FROM gradle:latest AS build

# Copiar los archivos del proyecto al contenedor
COPY --chown=gradle:gradle . /home/gradle/src

# Establecer el directorio de trabajo
WORKDIR /home/gradle/src

# Asegurarse de que las dependencias estén resueltas antes de hacer el build
RUN gradle clean build --warning-mode all --no-daemon

# Ejecutar gradle bootJar
RUN gradle bootJar --warning-mode all --no-daemon

#
# Package stage
#
FROM eclipse-temurin:17-jdk-jammy

# Argumento que define la ruta del archivo JAR generado
ARG JAR_FILE=build/libs/*.jar

# Copiar el archivo .jar desde la etapa de construcción
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

# Exponer el puerto (asegúrate de que el puerto esté configurado en la aplicación)
EXPOSE 8080

# Comando para ejecutar el archivo .jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
