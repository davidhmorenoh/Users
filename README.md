#Español

##Aplicación de Gestión de Usuarios

###Descripción
Esta aplicación está desarrollada en Java versión 22, utilizando Spring Boot versión 3.3.1 y administrada con Maven. La aplicación permite gestionar usuarios a través de una API RESTful.

###Requisitos
Java 22
Maven 3.x

###Configuración y Ejecución
1. Clone el repositorio:
```git
git clone [URL del repositorio]
cd [nombre del repositorio]
```

2. Construir el proyecto:
```shell
mvn clean install
```

3. Ejecutar la aplicación:
```shell
mvn spring-boot:run
```

4. La aplicación se ejecutará localmente en el puerto 8081.

###Base de Datos
La aplicación usa una base de datos H2 local.

###Estructura del Repositorio
- Code: Carpeta que contiene todo el código fuente.
- Database: Carpeta que contiene el script de la base de datos.

###Especificación OpenAPI
La especificación OpenAPI se encuentra en los recursos:

- /swagger-ui/**
- /v3/api-docs/**

###Autenticación
Todos los endpoints no requieren autenticación (inicio de sesión) cuando se ejecuta la aplicación.

###Arquitectura y Patrones de Diseño
La aplicación está desarrollada bajo una arquitectura orientada al dominio (patrón hexagonal) e implementa los siguientes patrones de diseño:

- Inyección de Dependencias (Dependency Injection)
- Configuración (Configuration)
- Facade
- Builder
- Decorator
- Singleton
- Strategy
Además, emplea POO, programación funcional, principios SOLID, KISS, DRY, YAGNI y clean code.

###Pruebas
La cobertura del código mediante pruebas unitarias es aproximadamente del 96%. Para probar los endpoints, utilice Swagger.

###Endpoints
- Obtener todos los usuarios (GET): /users
- Obtener usuario por ID (GET): /users/{id}
- Crear usuario (POST): /users
- Actualizar usuario (PUT): /users/{id}
- Habilitar usuario (PATCH): /users/{id}/enable
- Deshabilitar usuario (PATCH): /users/{id}/disable
- Eliminar usuario (DELETE): /users/{id}

###Diagrama de la Solución
- El diagrama de la solución está alojado en el siguiente enlace: Lucidchart

#English

##User Management Application

###Description
This application is developed in Java version 22, using Spring Boot version 3.3.1 and managed with Maven. The application allows managing users through a RESTful API.

###Requirements
Java 22
Maven 3.x

##Setup and Run
1. Clone the repository
```git
git clone [URL del repositorio]
cd [nombre del repositorio]
```

2. Build the project:
```shell
mvn clean install
```

3. Run the application:
```shell
mvn spring-boot:run
```

4. The application will run locally on port 8081.

###Database
The application uses a local H2 database.

###Repository Structure
- Code: Folder containing all the source code.
- Database: Folder containing the database script.

###OpenAPI Specification
The OpenAPI specification can be found in the resources:

- /swagger-ui/**
- /v3/api-docs/**

###Authentication
All endpoints do not require authentication (login) when the application is running.

###Architecture and Design Patterns
The application is developed under a domain-driven design (hexagonal pattern) and implements the following design patterns:

- Dependency Injection
Configuration
Facade
Builder
Decorator
Singleton
Strategy
Additionally, it employs OOP, functional programming, SOLID principles, KISS, DRY, YAGNI, and clean code.

###Testing
The code coverage through unit tests is approximately 96%. To test the endpoints, use Swagger.

##Endpoints
- Get all users (GET): /users
- Get user by ID (GET): /users/{id}
- Create user (POST): /users
- Update user (PUT): /users/{id}
- Enable user (PATCH): /users/{id}/enable
- Disable user (PATCH): /users/{id}/disable
- Delete user (DELETE): /users/{id}

###Solution Diagram
The solution diagram is hosted at the following link: Lucidchart