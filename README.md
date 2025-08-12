# API Rest Java Example

Ejemplo sencillo de un servicio API Rest para búsqueda de personas por número telefónico, desarrollado con Spring Boot 3.5.4 y Java 21.

## 📋 Características

- **Búsqueda de personas** por número telefónico
- **Documentación automática** con OpenAPI/Swagger
- **Arquitectura limpia** con separación de capas


## ⚙️ Requisitos

- **Java 21** o superior
- **Maven 3.6+**
- **IDE** compatible con Java (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/alejandro-vital/api-rest-java.git
cd api-rest-java-example
```

### 2. Compilar el proyecto

```bash
mvn clean compile
```

### 3. Ejecutar tests (opcional)

```bash
mvn test
```

### 4. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

### 5. Verificar que está funcionando

La aplicación estará disponible en: `http://localhost:8080`

## 📖 Uso de la API

### Endpoint Principal

**POST** `/api/personas/buscar`

Busca una persona por su número telefónico.

#### Request Body

```json
{
  "numeroTelefonico": "5551234567"
}
```

#### Response Exitoso (200)

```json
{
  "mensaje": "Persona encontrada exitosamente",
  "data": {
    "nombre": "Juan",
    "apellidoPaterno": "García",
    "apellidoMaterno": "López"
  },
  "fecha_hora": "2025-08-12T14:30:00"
}
```

#### Response Error (404)

```json
{
  "error": "Persona no encontrada",
  "mensaje": "No se encontró una persona con el número telefónico: 5559999999",
  "codigo": "PERSONA_NOT_FOUND",
  "fecha_hora": "2025-08-12T14:30:00"
}
```

### Datos de Prueba

El sistema incluye las siguientes personas para testing:

| Teléfono    | Nombre | Apellido Paterno | Apellido Materno |
|-------------|--------|------------------|------------------|
| 5551234567  | Juan   | García           | López            |
| 5552345678  | María  | Rodríguez        | Martínez         |
| 5553456789  | Carlos | Fernández        | González         |
| 5554567890  | Ana    | Morales          | Jiménez          |


## 📚 Documentación Interactiva

### Swagger UI

Una vez que la aplicación esté ejecutándose, puedes acceder a la documentación interactiva:

**URL:** `http://localhost:8080/swagger-ui.html`

Desde Swagger UI puedes:
- ✅ Ver toda la documentación de la API
- ✅ Probar los endpoints directamente
- ✅ Ver ejemplos de requests y responses
- ✅ Validar diferentes códigos de error

### OpenAPI JSON

También puedes obtener la especificación OpenAPI en formato JSON:

**URL:** `http://localhost:8080/api-docs`

## ⚠️ Validaciones

El número telefónico debe cumplir:
- **Obligatorio**: No puede estar vacío
- **Formato**: Exactamente 10 dígitos numéricos
- **Patrón**: Solo números del 0-9

Ejemplos válidos: `5551234567`, `5552345678`
Ejemplos inválidos: `555-123-4567`, `555123456`, `abcd123456`

## 🏗️ Arquitectura

El proyecto sigue una **arquitectura limpia** con separación clara de responsabilidades:

```
src/main/java/com/avital/api/rest/java/example/
├── application/          # Capa de aplicación
│   ├── dto/             # Data Transfer Objects
│   ├── exceptions/      # Excepciones de dominio
│   └── services/        # Servicios de aplicación
├── domain/              # Capa de dominio
│   ├── entities/        # Entidades de negocio
│   ├── repositories/    # Interfaces de repositorios
│   └── usecases/        # Casos de uso
└── infrastructure/      # Capa de infraestructura
    ├── controllers/     # Controladores REST
    └── repositories/    # Implementaciones de repositorios
```
## 🧪 Pruebas
El proyecto incluye pruebas unitarias y de integración para asegurar el correcto funcionamiento de la lógica de negocio.
Puedes ejecutar las pruebas con el siguiente comando:

```bash
mvn test
```