FROM eclipse-temurin:17-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /root

COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root



# COPIAR EL CODIGO FUENTE DENTRO DEL CONTENEDOR
COPY ./src /root/src

# CONSTRUIR NUESTRA APLICACION
RUN ./mvnw clean install -DskipTests
# Dar permisos de ejecución al script mvnw
RUN chmod +x /root/mvnw
RUN ./mvnw dependency:go-offline
# Construir la aplicación
RUN ./mvnw clean install -DskipTests


# Exponer el puerto
EXPOSE 8080

# Comando de entrada
ENTRYPOINT ["java", "-jar", "/root/target/yop-0.0.1-SNAPSHOT.jar"]
