# Usa una imagen de Java 11 (puedes usar otra versión si es necesario)
FROM openjdk:17-slim
# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR desde tu máquina local al contenedor
COPY target/ejercicio-1.0-SNAPSHOT.jar /app/ejercicio-1.0-SNAPSHOT.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 7000

# Comando para ejecutar el archivo JAR
CMD ["java", "-jar", "ejercicio-1.0-SNAPSHOT.jar"]