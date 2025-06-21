# Sistema de Gestión Logística

Este proyecto implementa una API REST para gestión de envíos terrestres y marítimos utilizando arquitectura hexagonal y Spring Boot.

## Estructura del Proyecto
```bash
backend-logistic-app/
├── src/
│   ├── main/
│   │   ├── java/com/logistics/
│   │   │   ├── application/
│   │   │   │   └── service/
│   │   │   │       ├── LandShipmentService.java
│   │   │   │       └── MaritimeShipmentService.java
│   │   │   ├── domain/
│   │   │   │   ├── model/
│   │   │   │   │   ├── Client.java
│   │   │   │   │   ├── Product.java
│   │   │   │   │   ├── Warehouse.java
│   │   │   │   │   ├── Port.java
│   │   │   │   │   ├── LandShipment.java
│   │   │   │   │   └── MaritimeShipment.java
│   │   │   │   └── port/
│   │   │   │       ├── in/
│   │   │   │       │   ├── CreateLandShipmentUseCase.java
│   │   │   │       │   └── CreateMaritimeShipmentUseCase.java
│   │   │   │       └── out/
│   │   │   │           ├── SaveLandShipmentPort.java
│   │   │   │           └── SaveMaritimeShipmentPort.java
│   │   │   ├── infrastructure/
│   │   │   │   ├── adapter/
│   │   │   │   │   ├── in/
│   │   │   │   │   │   ├── LandShipmentController.java
│   │   │   │   │   │   └── MaritimeShipmentController.java
│   │   │   │   │   └── out/
│   │   │   │   │       ├── LandShipmentRepositoryImpl.java
│   │   │   │   │       └── MaritimeShipmentRepositoryImpl.java
│   │   │   │   ├── config/
│   │   │   │   │   ├── SecurityConfig.java
│   │   │   │   │   └── SwaggerConfig.java
│   │   │   │   └── security/
│   │   │   │       ├── JwtTokenProvider.java
│   │   │   │       └── JwtUserDetailsService.java
│   │   │   └── BackendLogisticAppApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── docs/
│   ├── database/
│   │   ├── schema.dbml
│   │   └── schema.png
│   └── diagrams/
│       ├── architecture.puml
│       └── plantuml-diagram.svg
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── init.sql
└── pom.xml
```

## Requisitos Previos

- Java 17 o superior
- Maven 3.6 o superior
- Docker y Docker Compose
- PostgreSQL (si se ejecuta localmente sin Docker)

## Configuración y Ejecución

1. **Clonar el Repositorio**
```bash
git clone [https://github.com/kevin3080/logistic-app.git](https://github.com/kevin3080/logistic-app.git) 
cd backend-logistic-app
```

. **Configurar Variables de Entorno**
   - Revisa y ajusta los valores en `application.properties` y `docker-compose.yml`
   - La clave JWT debe ser la misma en ambos archivos

3. **Compilar el Proyecto**
mvn clean package -DskipTests

4. **Iniciar con Docker Compose**

La aplicación estará disponible en:
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- Base de datos PostgreSQL: localhost:5432

## Autenticación

La API utiliza JWT para autenticación. Para obtener un token:

```bash
curl -X POST "[http://localhost:8080/authenticate](http://localhost:8080/authenticate)"
-H "Content-Type: application/json"
-d '{"username":"admin", "password":"password"}'
```

Credenciales por defecto:
- Usuario: admin
- Contraseña: password

## Endpoints Principales

### Envíos Terrestres
- GET `/api/land-shipments` - Listar todos los envíos terrestres
- POST `/api/land-shipments` - Crear nuevo envío terrestre
- GET `/api/land-shipments/{id}` - Obtener envío por ID
- PUT `/api/land-shipments/{id}` - Actualizar envío
- DELETE `/api/land-shipments/{id}` - Eliminar envío

### Envíos Marítimos
- GET `/api/maritime-shipments` - Listar todos los envíos marítimos
- POST `/api/maritime-shipments` - Crear nuevo envío marítimo
- GET `/api/maritime-shipments/{id}` - Obtener envío por ID
- PUT `/api/maritime-shipments/{id}` - Actualizar envío
- DELETE `/api/maritime-shipments/{id}` - Eliminar envío

## Arquitectura

El proyecto sigue una arquitectura hexagonal (ports and adapters) con tres capas principales:

1. **Domain**: Contiene las entidades del negocio y los puertos (interfaces)
2. **Application**: Implementa la lógica de negocio
3. **Infrastructure**: Contiene adaptadores para REST API y persistencia

## Base de Datos

El esquema incluye las siguientes tablas:
- app_users
- clients
- products
- warehouses
- ports
- land_shipments
- maritime_shipments

Los datos iniciales se cargan automáticamente desde `init.sql`.

## Seguridad

- Autenticación basada en JWT
- Roles: ROLE_USER, ROLE_ADMIN
- Endpoints protegidos requieren token válido
- Contraseñas encriptadas con BCrypt

## Diagramas del Proyecto

### Estructura de la Base de Datos
El diagrama de la base de datos se encuentra en `docs/database/schema.dbml`. 
Puedes visualizarlo en [dbdiagram.io](https://dbdiagram.io) copiando el contenido del archivo.

### Arquitectura del Sistema
El diagrama de arquitectura se encuentra en `docs/diagrams/architecture.puml`.
Puedes visualizarlo usando cualquier editor compatible con PlantUML como:
- Plugin de IntelliJ IDEA
- Visual Studio Code con extensión PlantUML
- [PlantUML Web Server](http://www.plantuml.com/plantuml/uml/)


## Documentación API

La documentación completa de la API está disponible en Swagger UI:
http://localhost:8080/swagger-ui.html

## Desarrollo

Para desarrollo local sin Docker:

1. Configura una base de datos PostgreSQL local
2. Actualiza `application.properties` con las credenciales locales
3. Ejecuta la aplicación:
```bash 
mvn spring-boot:run
```

## Pruebas

Ejecutar pruebas unitarias:
```bash
mvn test
```


