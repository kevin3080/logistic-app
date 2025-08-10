# Sistema de Gestión Logística
Este proyecto es una solución de software diseñada para una empresa de logística que gestiona tanto operaciones terrestres como marítimas. Permite registrar clientes, productos, envíos, y gestionar bodegas y puertos a nivel nacional e internacional.

En esta aplicación, aunque la estructura tiene apariencia de **monorepo**, en realidad utiliza **submódulos de Git** para gestionar de manera independiente el código del backend. Este enfoque arquitectónico permite separar claramente las responsabilidades, facilitando tanto el mantenimiento como las integraciones con herramientas de CI/CD.

El submódulo del backend contiene la implementación completa del servidor desarrollado en **Java con Spring Boot** y está preparado para su uso en entornos tanto de desarrollo como de producción, garantizando eficiencia y consistencia.

En el futuro, está planificado implementar el frontend bajo la misma estrategia, utilizando submódulos independientes. Esto permitirá una separación aún más clara entre los repositorios, simplificando el desarrollo colaborativo e integrando con mayor facilidad los flujos de CI/CD.

---

## Inicializar y actualizar Sub-módulos
cuando alguien clona el repositorio por primera vez, debe de ejecutar el siguiente comando para inicializar y actualizar los sub-módulos

```bash
  git submodule update --init --recursive
```

Para actualizar las referencias de los sub-módulos:
```bash
  git submodule update --remote
```

## ⚠️ Importante
**Solo para desarrollo:**

- **Paso 1:** Si estás trabajando con un repositorio que contiene submódulos, primero actualiza y realiza el push de los cambios en el submódulo.
- **Paso 2:** Luego realiza el push en el repositorio principal.

> **Nota:**  
> Si haces esto al revés, las referencias de los submódulos en el repositorio principal se perderán, lo que puede provocar conflictos que deberán resolverse manualmente.


## CI/CD Integrado con GitHub Actions
En el submódulo de backend, se han configurado workflows en **GitHub Actions** que realizan las siguientes tareas automáticamente al manejar el proceso de integración y despliegue continuo:
1. **Construcción de Imagen Docker**:
   - El pipeline crea una imagen Docker para el backend. Esta imagen contiene la aplicación lista para ejecutarse, asegurando que todo funcione de manera consistente en cualquier entorno.

> **Nota importante**: La base de datos no se incluye en este contenedor Docker, siguiendo las buenas prácticas de Docker, donde cada contenedor debe tener un propósito único. El contenedor del backend actúa únicamente como API.
> 

2. **Despliegue Automático**:
   - Si la build y las pruebas tienen éxito, la imagen Docker puede ser empujada a un registro, lista para ser utilizada en un entorno de producción.
3. **Imagen de Docker Hub**:
   - La imagen del backend se encuentra disponible públicamente en [Docker Hub](https://hub.docker.com/r/kevinpernia/backend-logistic-app). Puedes encontrar más detalles sobre cómo ejecutarla correctamente en su descripción.


## 🔧 Modo Desarrollo
Para facilitar el desarrollo, la configuración incluye un archivo **docker-compose.yml** que permite ejecutar el backend y la base de datos **PostgreSQL** localmente con un solo comando.
Características clave del modo desarrollo:
- **Base de Datos Local**: El contenedor de PostgreSQL es iniciado junto con la API para simular un entorno cercano a producción.
- **Script de Inicialización - `init.sql`**: Proporciona datos de ejemplo útiles para las pruebas y validaciones locales.
- **Swagger UI**: La API está completamente documentada y se accede a la interfaz Swagger desde `http://localhost:8080/swagger-ui/index.html`.

Esto permite:
- Probar endpoints y configurar clientes fácilmente durante el desarrollo.
- Validar funcionalidades sin la necesidad de una base de datos externa inicializada manualmente.

## 📄 Documentación del Backend
El submódulo del backend se encuentra completamente documentado, tanto en su archivo como mediante **Swagger UI**. En el README se detallan aspectos clave como: `README.md`
- Cómo inicializar el entorno de desarrollo.
- Uso del archivo `docker-compose.yml`.
- Pasos para inicializar la base de datos usando el script `init.sql`.

Esto asegura que los desarrolladores puedan comenzar a trabajar con el backend de manera rápida y clara.


## 🧱 Arquitectura General

- ✅ **Backend**: Java con Spring Boot
  - Arquitectura Hexagonal (Ports & Adapters)
  - Principios SOLID y Clean Code
  - API RESTful con validaciones, manejo de errores y JWT
  - Base de datos: PostgreSQL
  - Pruebas unitarias con JUnit (🚧 en desarrollo)
  - Dockerizado
  - CI/CD con Github Actions
  - Pre commit y Pre Push con Lefthook
- ✅ **Frontend**: Angular (🚧 en desarrollo)

