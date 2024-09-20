# 🛍️ **Ecommerce Backend - Gestión de Productos y Pedidos**

Este proyecto es un servidor backend diseñado para la **gestión de productos y pedidos** de un ecommerce. Está implementado con **Java Reactivo (versión 17)**, **Spring Boot**, y otras tecnologías modernas, permitiendo realizar operaciones **CRUD** (Crear, Leer, Actualizar y Eliminar) sobre productos y pedidos de manera eficiente.

## 🚀 **Requisitos**

Antes de ejecutar el proyecto, asegúrate de tener instalados los siguientes componentes:

- 🐳 **Docker** (para contenedores de base de datos)
- 🐧 **WSL** (Windows Subsystem for Linux, en caso de usar Windows)
- 🛢️ **PostgreSQL**
- ☕ **Java 17** o superior
- 📦 **Maven** (para la gestión de dependencias)

## 🛠️ **Tecnologías Utilizadas**

Este proyecto utiliza las siguientes tecnologías:

- **Lenguaje**: Java Reactivo 17
- **Framework**: Spring Boot
- **Reactivo**: Project Reactor
- **ORM**: R2DBC (Reactive Relational Database Connectivity)
- **Utilidad**: Lombok (para reducir código repetitivo)
- **Documentación**: OpenAPI Swagger
- **Análisis de Cobertura**: JaCoCo
- **Calidad de Código**: SonarQube
- **Autenticación**: JWT (JSON Web Tokens)
- **Service Discovery**: Eureka Server

## 🏗️ **Arquitectura del Proyecto**

A continuación se presenta la arquitectura general del proyecto, donde se implementan patrones de **servicio** y **mapper**. Además, el proyecto sigue un enfoque reactivo con **Project Reactor** para una mejor eficiencia en aplicaciones no bloqueantes.

![Arquitectura del Proyecto](https://drive.google.com/file/d/13lIaKvWbr-5RbEChTcxclAYpqNCIAcx8/view?usp=sharing)

## 📝 **Ejecución del Proyecto**

Sigue los pasos a continuación para clonar y ejecutar el proyecto en tu entorno local.

### 1. **Clonar el Repositorio**

```bash
git clone https://github.com/CrisAcosMoya/Technical-test.git
cd proyecto-ecommerce
```


### 2. **Construir el Proyecto con Docker Compose**

Primero, construye la imagen Docker del proyecto:

```bash
docker-compose build
```
Luego, levanta los contenedores:

```bash
docker-compose up
```

### 3. **Levantar Contenedores Adicionales**

Asegúrate de ejecutar los siguientes contenedores para que el proyecto funcione correctamente:

**Keycloak (para autenticación con JWT):**

```bash
docker run -d -p 8080:8080 \
-e KEYCLOAK_ADMIN=admin \
-e KEYCLOAK_ADMIN_PASSWORD=admin \
quay.io/keycloak/keycloak:24.0.2
```

**PostgreSQL (base de datos):**

```bash
docker run --name postgres_db \
-e POSTGRES_PASSWORD=1234 \
-e POSTGRES_USER=root \
-e POSTGRES_DB=base \
-d -p 5432:5432 \
-v C:\Users\docker\volumes\postgres:/var/lib/postgresql/data -d postgres
```

**SonarQube (para análisis de código):**

```bash
docker pull sonarqube
docker run --name sonarqube -p 9000:9000 sonarqube:latest
```

### 4. **Ejecutar el Proyecto en Local**(Opcional)*

Asegúrate de que el puerto configurado sea 8111. Luego de que la base de datos y los servicios estén funcionando correctamente, ejecuta el servidor Spring Boot con:

```bash
mvn spring-boot:run
```

El servidor estará disponible en: http://localhost:8111.

### 5. **Acceder a la Documentación Swagger**

Para explorar los endpoints y realizar pruebas, accede a la documentación Swagger disponible en el siguiente enlace:

[🔗 Swagger UI - Localhost](http://localhost:8111/swagger-ui.html)

🔧 Notas Adicionales

El servidor está configurado por defecto para usar el puerto 8111. Si necesitas cambiarlo, puedes hacerlo en el archivo de configuración application.yaml.

Asegúrate de que Docker esté ejecutándose correctamente antes de iniciar la aplicación.

Para acceder a SonarQube, abre tu navegador en: http://localhost:9000.
