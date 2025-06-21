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
│   │   │   │       ├── ClientService.java
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
│   │   │   │       │   ├── CreateClientUseCase.java
│   │   │   │       │   ├── CreateLandShipmentUseCase.java
│   │   │   │       │   ├── CreateMaritimeShipmentUseCase.java
│   │   │   │       │   ├── GetClientUseCase.java
│   │   │   │       │   ├── GetLandShipmentUseCase.java
│   │   │   │       │   ├── GetMaritimeShipmentUseCase.java
│   │   │   │       │   ├── UpdateClientUseCase.java
│   │   │   │       │   ├── UpdateLandShipmentUseCase.java
│   │   │   │       │   ├── UpdateMaritimeShipmentUseCase.java
│   │   │   │       │   ├── DeleteClientUseCase.java
│   │   │   │       │   ├── DeleteLandShipmentUseCase.java
│   │   │   │       │   └── DeleteMaritimeShipmentUseCase.java
│   │   │   │       └── out/
│   │   │   │           ├── SaveClientPort.java
│   │   │   │           ├── SaveLandShipmentPort.java
│   │   │   │           └── SaveMaritimeShipmentPort.java
│   │   │   ├── infrastructure/
│   │   │   │   ├── adapter/
│   │   │   │   │   ├── in/
│   │   │   │   │   │   ├── controller/
│   │   │   │   │   │   │   ├── ClientController.java
│   │   │   │   │   │   │   ├── LandShipmentController.java
│   │   │   │   │   │   │   └── MaritimeShipmentController.java
│   │   │   │   │   │   └── dto/
│   │   │   │   │   │       ├── ClientRequest.java
│   │   │   │   │   │       ├── LandShipmentRequest.java
│   │   │   │   │   │       ├── MaritimeShipmentRequest.java
│   │   │   │   │   │       ├── AuthRequest.java
│   │   │   │   │   │       └── AuthResponse.java
│   │   │   │   │   └── out/
│   │   │   │   │       ├── persistence/
│   │   │   │   │   │   │   ├── adapter/
│   │   │   │   │   │   │   │   ├── ClientRepositoryImpl.java
│   │   │   │   │   │   │   │   ├── ProductRepositoryImpl.java
│   │   │   │   │   │   │   │   ├── WarehouseRepositoryImpl.java
│   │   │   │   │   │   │   │   ├── PortRepositoryImpl.java
│   │   │   │   │   │   │   │   ├── LandShipmentRepositoryImpl.java
│   │   │   │   │   │   │   │   └── MaritimeShipmentRepositoryImpl.java
│   │   │   │   │   │   │   └── repository/
│   │   │   │   │   │   │       ├── SpringDataClientRepository.java
│   │   │   │   │   │   │       ├── SpringDataProductRepository.java
│   │   │   │   │   │   │       ├── SpringDataWarehouseRepository.java
│   │   │   │   │   │   │       ├── SpringDataPortRepository.java
│   │   │   │   │   │   │       ├── SpringDataLandShipmentRepository.java
│   │   │   │   │   │   │       └── SpringDataMaritimeShipmentRepository.java
│   │   │   │   ├── config/
│   │   │   │   │   ├── SecurityConfig.java
│   │   │   │   │   └── SwaggerConfig.java
│   │   │   │   ├── exception/
│   │   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   │   ├── InvalidDataException.java
│   │   │   │   │   └── DuplicateGuideNumberException.java
│   │   │   │   └── security/
│   │   │   │       ├── JwtRequestFilter.java
│   │   │   │       ├── JwtUserDetailsService.java
│   │   │   │       ├── JwtUtil.java
│   │   │   │       ├── User.java
│   │   │   │       └── UserRepository.java
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
git clone https://github.com/kevin3080/logistic-app.git
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

Una vez que tengas el token, úsalo en el encabezado Authorization: Bearer YOUR_JWT_TOKEN para todas las solicitudes a los endpoints protegidos.
```bash
Authorization: Bearer YOUR_JWT_TOKEN
```


## Endpoints Principales
A continuación, se detallan los endpoints disponibles con ejemplos de cómo interactuar con ellos usando curl. (puedes usar POSTMAN de igual manera)

Nota: Reemplaza YOUR_JWT_TOKEN con el token real que obtuviste.

### Registrar nuevo cliente
```bash
curl -X POST "http://localhost:8080/api/clients" \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN" \
     -d '{
           "name": "Cliente Demo",
           "email": "demo.client@example.com",
           "phone": "+1234567890"
         }'
```

### Listar todos los clientes
```bash
curl -X GET "http://localhost:8080/api/clients" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN"
```
### Obtener cliente por ID
```bash
curl -X GET "http://localhost:8080/api/clients/CLIENT_UUID" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Actualizar cliente
```bash
curl -X PUT "http://localhost:8080/api/clients/CLIENT_UUID" \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN" \
     -d '{
           "name": "Cliente Demo Actualizado",
           "email": "demo.client.updated@example.com",
           "phone": "+1122334455"
         }'
```
### Eliminar cliente
```bash
curl -X DELETE "http://localhost:8080/api/clients/CLIENT_UUID" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Envíos Terrestres

### Crear nuevo envío terrestre
```bash
curl -X POST "http://localhost:8080/api/land-shipments" \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN" \
     -d '{
           "clientId": "CLIENT_UUID",
           "productId": "PRODUCT_UUID",
           "quantity": 12,
           "registrationDate": "2024-06-20",
           "deliveryDate": "2024-06-25",
           "warehouseId": "WAREHOUSE_UUID",
           "shippingCost": 150.00,
           "vehiclePlate": "ABC123",
           "guideNumber": "LANDSHP001A"
         }'
```

### Listar envíos terrestres
```bash
curl -X GET "http://localhost:8080/api/land-shipments" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN"
```
### Obtener envío terrestre por ID
```bash
curl -X GET "http://localhost:8080/api/land-shipments/SHIPMENT_UUID" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Actualizar envío terrestre
```bash
curl -X PUT "http://localhost:8080/api/land-shipments/SHIPMENT_UUID" \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN" \
     -d '{
           "clientId": "CLIENT_UUID",
           "productId": "PRODUCT_UUID",
           "quantity": 15,
           "registrationDate": "2024-06-20",
           "deliveryDate": "2024-06-30",
           "warehouseId": "WAREHOUSE_UUID",
           "shippingCost": 160.00,
           "vehiclePlate": "DEF456",
           "guideNumber": "LANDSHP001A"
         }'
```

### Obtener envío terrestre por ID
```bash
curl -X DELETE "http://localhost:8080/api/land-shipments/SHIPMENT_UUID" \
     -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Envíos Marítimos
Sigue la misma convencion que los terrestes

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


