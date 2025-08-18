## 🚢 Sistema de Gestión Logística

Este proyecto es una solución de software para una empresa de logística que gestiona operaciones terrestres y marítimas.
Permite registrar clientes, productos, envíos, y administrar bodegas y puertos a nivel nacional e internacional.

El repositorio principal contiene dos submódulos de Git:

**Backend** → Java 17 + Spring Boot 3.5.3, arquitectura hexagonal (Ports & Adapters), autenticación con JWT, base de datos PostgreSQL, pruebas con JUnit, dockerizado.

**Frontend** → Angular 20 con SCSS, Angular Material y TailwindCSS, organizado bajo una arquitectura modular por features para mantener el código limpio y escalable.

---

### ⚙️ Inicialización del Proyecto

Clona el repositorio principal junto con sus submódulos:

```bash
  git clone --recursive https://github.com/kevin3080/logistic-app.git
```

Si ya lo clonaste sin --recursive, ejecuta:

```bash
  git submodule update --init --recursive
```

### 🔄 Actualizar Submódulos

Para traer los últimos cambios de los submódulos (backend y frontend):

```bash
  git submodule update --remote
```

### 🐳 Levantar Todo con Docker Compose

El proyecto está completamente dockerizado.
Desde la raíz del repositorio principal, ejecuta:

```bash
  docker compose up --build
```

**Esto levantará:**

 1. Backend API → disponible en http://localhost:8080
 2. Swagger UI → http://localhost:8080/swagger-ui/index.html
 3. Base de Datos PostgreSQL → en 127.0.0.1:5433
 4. Frontend Angular → disponible en http://localhost:3000

Usuario generado automáticamente por la migración en el proceso del build (`init.sql`)
>  1. usuario: admin
>  2. password: password

Puedes verificar el estado de los contenedores con:

```bash
  docker ps
```

### 📂 Arquitectura General

#### Backend

 - Lenguaje: Java 17
 - Framework: Spring Boot 3.5.3
 - Arquitectura: Hexagonal (Ports & Adapters)
 - Base de datos: PostgreSQL
 - Documentación: Swagger UI
 - Docker + CI/CD con GitHub Actions

#### Frontend

 - Framework: Angular 20
 - Estilos: SCSS + TailwindCSS + Angular Material
 - Arquitectura modular (Core / Shared / Features)


### 🚧 Modo Desarrollo

#### Backend

```bash
  cd backend-logistic-app
  docker compose up --build
```

#### Frontend

**Servir en modo desarrollo:**

```bash
  cd frontend-logistic-app
  npm install
  ng serve  # tener CLI de angular o en su defecto usar npm start
```


### 📌 Buenas Prácticas con Submódulos

Realiza commits y push dentro del submódulo (backend o frontend).

Luego actualiza la referencia en el repo principal:

```bash
   git add <submodulo>
   git commit -m "chore: update submodule reference"
   git push
```

> [!NOTE]
> **[!INFO]** Si haces push en el repo principal antes de actualizar el submódulo, se pueden perder referencias y generar conflictos.

### 🧱 Tecnologías

 - Java 17
 - Spring Boot 3.5.3
 - PostgreSQL 15
 - Angular 20
 - Angular Material
 - TailwindCSS
 - Docker & Docker Compose
 - GitHub Actions (CI/CD)
