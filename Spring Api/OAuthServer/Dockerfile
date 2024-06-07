# Usa una imagen base de Maven para compilar tu proyecto
FROM maven:3.8.4-openjdk-17-slim AS build

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo pom.xml al contenedor
COPY pom.xml .

# Copia todo el código fuente al contenedor
COPY src ./src

# Compila el proyecto
RUN mvn package -DskipTests

# Crea una imagen de Java para ejecutar tu aplicación
FROM openjdk:17-jdk-slim AS runtime

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR generado en la etapa de compilación al contenedor
COPY --from=build /app/target/OAuthServer-0.0.1-SNAPSHOT.jar ./app.jar

# Expone el puerto en el que se ejecuta tu aplicación Spring
EXPOSE 9000

# Comando para ejecutar tu aplicación Spring al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]
