# Versión en español

## Aplicación de gestión de usuarios

### Descripción

Esta aplicación está desarrollada en Java versión 22, utilizando Spring Boot versión 3.3.1 y administrada con Maven. La aplicación permite gestionar usuarios a través de una API RESTful.

### Requisitos

- Java 22
- Maven 4.0.0

### Configuración y ejecución

1. **Clone el repositorio**:

```bash
git clone https://github.com/davidhmorenoh/Users.git
cd Users
```

2. **Construir el proyecto**:
```bash
mvn clean install
```

3. **Ejecutar la aplicación**:
```bash
mvn spring-boot:run
```

4. **La aplicación se ejecutará localmente en el puerto 8081**.

### Base de datos

La aplicación usa una base de datos H2 local y tiene una inicialización de datos mediante un script ubicado `src/main/resources/data.sql`.

### Especificación OpenAPI

La especificación OpenAPI se encuentran disponibles en los siguientes recursos:

- [Swagger UI](http://localhost:8081/v1/swagger-ui/)
- [OpenAPI Spec](http://localhost:8081/v1/v3/api-docs/)

Para probar los endpoints, utilice Swagger UI.

### Endpoints públicos

Todos los endpoints no requieren autenticación (inicio de sesión) cuando se ejecuta la aplicación.

### Arquitectura, patrones de diseño, principios y buenas practicas de desarrollo de software

La aplicación está desarrollada bajo una arquitectura orientada al dominio (patrón hexagonal) e implementa una combinación de patrones de diseño, principios de desarrollo y buenas prácticas para garantizar su modularidad, mantenibilidad y eficiencia. A continuación, se detallan los enfoques y principios aplicados:

- **Dependency Injection**: Inyección de dependencias para mejorar la modularidad y facilitar las pruebas unitarias.
- **Configuration**: Configuración centralizada para facilitar la gestión de propiedades y ajustes de la aplicación desde un único lugar.
- **Facade**: Simplificación de la interacción con subsistemas complejos, proporcionando una interfaz unificada.
- **Builder**: Construcción de objetos complejos de manera controlada y flexible.
- **Singleton**: Garantía de una única instancia de ciertas clases, controlando su acceso global.
- **Decorator**: Permite añadir comportamiento a objetos de manera flexible y dinámica.
- **Strategy**: Selección de algoritmos en tiempo de ejecución, promoviendo la flexibilidad y reutilización.
- **POO (Programación orientada a objetos)**: Utiliza principios de encapsulación, abstracción, herencia y polimorfismo para estructurar el código.
- **Programación funcional**: Uso de funciones como ciudadanos de primera clase para promover la inmutabilidad y las funciones puras.
- **SOLID**: Principios de diseño para crear software flexible, escalable y mantenible.
- **KISS**: Mantener las cosas simples y directas.
- **DRY**: No repetir el código, promoviendo la reutilización y la eficiencia.
- **YAGNI**: No construir funcionalidades innecesarias.
- **Clean Code**: Código legible, claro y mantenible, facilitando la colaboración y la escalabilidad.

Estos enfoques y principios aseguran que la aplicación no solo cumpla con sus objetivos funcionales, sino que también sea robusta, fácil de mantener y extensible a largo plazo.

### Pruebas unitarias

La cobertura del código mediante pruebas unitarias es aproximadamente del 96%.

### Endpoints

- Obtener todos los usuarios (GET): /users
- Obtener usuario por ID (GET): /users/{id}
- Crear usuario (POST): /users
- Actualizar usuario (PUT): /users/{id}
- Habilitar usuario (PATCH): /users/{id}/enable
- Deshabilitar usuario (PATCH): /users/{id}/disable
- Eliminar usuario (DELETE): /users/{id}

### Diagrama de la solución

El diagrama realiza una representación a muy alto nivel sobre la interacción del proyecto en sus diferentes capas y compornentes con sus respectivos actores:

- Las capas del proyecto siguiendo la arquitectura hexágonal están nombradas y se representan en un recuadro con un color en específico.
- Los actores, componentes e interacciones se representan según el caso con una figura alusiva y con el mismo color de la capa en donde están contenidas.
- Las interacciones entre capas se representan con el color azul.

![Diagrama](/Diagram.jpg)

### Contribuciones

Las contribuciones son bienvenidas. Para contribuir, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadir nueva característica'`).
4. Empuja tu rama (`git push origin feature/nueva-caracteristica`).
5. Abre un Pull Request.

### Licencia

Este proyecto está licenciado bajo los términos de la [MIT License](LICENSE).

---

Este README proporciona una guía completa para configurar, ejecutar, entender y contribuir al proyecto. Si tienes alguna pregunta o encuentras algún problema, no dudes en abrir un issue en el repositorio.

---

# English version

## User management application

### Description

This application is developed in Java version 22, using Spring Boot version 3.3.1, and managed with Maven. The application allows user management through a RESTful API.

### Requirements

Java 22
Maven 4.0.0

### Setup and run

1. **Clone the repository**:

```bash
git clone https://github.com/davidhmorenoh/Users.git
cd Users
```
2. **Build the project**:

```bash
mvn clean install
```

3. **Run the application**:

```bash
mvn spring-boot:run
```

4. **The application will run locally on port 8081**.

### Database

The application uses a local H2 database and has data initialization using a script located `src/main/resources/data.sql`.

### OpenAPI specification

The OpenAPI specification is available at the following resources:

- [Swagger UI](http://localhost:8081/v1/swagger-ui/)
- [OpenAPI Spec](http://localhost:8081/v1/v3/api-docs/)

To test the endpoints, use Swagger UI.

### Public endpoints

All endpoints do not require authentication (login) when the application is running.

### Architecture, design patterns, principles, and best practices in software development

The application is developed under a domain-oriented architecture (hexagonal pattern) and implements a combination of design patterns, development principles, and best practices to ensure its modularity, maintainability, and efficiency. The following approaches and principles are applied:

- **Dependency Injection**: Improves modularity and facilitates unit testing by injecting dependencies.
- **Configuration**: Centralized configuration to efficiently manage properties and settings from a single place.
- **Facade**: Simplifies interaction with complex subsystems by providing a unified interface.
- **Builder**: Facilitates controlled and flexible construction of complex objects.
- **Singleton**: Ensures a single instance for certain classes, controlling global access.
- **Decorator**: Allows adding behavior to objects in a flexible and dynamic manner.
- **Strategy**: Facilitates algorithm selection at runtime, promoting flexibility and reuse.
- **Object-oriented programming (OOP)**: Uses principles of encapsulation, abstraction, inheritance, and polymorphism to structure the code.
- **Functional programming**: Uses functions as first-class citizens to promote immutability and pure functions.
- **SOLID**: Design principles for creating flexible, scalable, and maintainable software.
- **KISS**: Keep things simple and straightforward.
- **DRY**: Do not repeat code, promoting reuse and efficiency.
- **YAGNI**: Do not build unnecessary features.
- **Clean Code**: Readable, clear, and maintainable code, facilitating collaboration and scalability.

These approaches and principles ensure that the application not only meets its functional goals but is also robust, easy to maintain, and extensible in the long term.

### Unit testing

The code coverage through unit tests is approximately 96%.

### Endpoints

- Get all users (GET): /users
- Get user by ID (GET): /users/{id}
- Create user (POST): /users
- Update user (PUT): /users/{id}
- Enable user (PATCH): /users/{id}/enable
- Disable user (PATCH): /users/{id}/disable
- Delete user (DELETE): /users/{id}

### Solution diagram

The diagram makes a very high level representation of the interaction of the project in its different layers and behaviors with their respective actors:

- The layers of the project following the hexagonal architecture are named and represented in a box with a specific color.
- The actors, components and interactions are represented as appropriate with an allusive figure and with the same color of the layer in which they are contained.
- Interactions between layers are represented with the color blue.

![Diagram](/Diagram.jpg)

### Contributions

Contributions are welcome. To contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/nueva-caracteristica`).
3. Make your changes and commit (`git commit -am 'Add new feature'`).
4. Push your branch (`git push origin feature/new-feature`).
5. Open a Pull Request.

### License

This project is licensed under the terms of the [MIT License](LICENSE).

---

This README provides a comprehensive guide to setup, run, understand, and contribute to the project. If you have any questions or encounter any issues, please feel free to open an issue in the repository.

---