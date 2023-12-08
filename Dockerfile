FROM amazoncorretto:17-alpine3.16-full as builder

WORKDIR /app

COPY . .

RUN ./gradlew clean bootJar

# Mover el archivo jar a otro directorio temporal
RUN mkdir /temp && mv /app/build/libs/CuentaBancariaWebApplication-0.0.1.jar /temp/app.jar

# Eliminar todos los archivos en /app
RUN rm -rf /app/*

# Mover el archivo jar de nuevo a /app
RUN mv /temp/app.jar /app/app.jar

FROM amazoncorretto:17-alpine3.16-full

COPY --from=builder /app/app.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]