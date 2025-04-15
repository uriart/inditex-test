# INDITEX Test API

Este proyecto es una API desarrollada en Java 21 y Spring Boot como parte de una prueba técnica. La API expone un endpoint para consultar el precio aplicable de un producto según la fecha, el identificador del producto y el de la marca.

## Requisitos

- Java 21
- Maven 3.6+

---

## Compilación y ejecución manual

### Compilar el proyecto

```bash
mvn clean package
```
### Ejecutar la aplicación

```bash
java -jar target/inditex-test*.jar
```

## Uso con Docker

### Construir la imagen
```bash
docker build . -t inditex-test:1.0.0
```

### Ejecutar el contenedor
```bash
docker run -p 8080:8080 inditex-test:1.0.0
```

## Documentación del API

Una vez la aplicación esté en ejecución, puedes acceder a la documentación interactiva de la API Swagger en:

http://localhost:8080/swagger-ui/index.html

## Testing

### Ejecutar tests unitarios
```bash
mvn test
```
### Ejecutar tests e2e (cucumber)
```bash
mvn -Dtest=com.inditex.demo.cucumber.CucumberTestRunner test
```