# API Rest Java Example

Ejemplo sencillo de un servicio API Rest para búsqueda de personas por número telefónico, desarrollado con Spring Boot 3.5.4 y Java 21.

## 📋 Características

- **Búsqueda de personas** por número telefónico
- **Validación de entrada** con Bean Validation
- **Manejo de errores** centralizado
- **Documentación automática** con OpenAPI/Swagger
- **Logs estructurados** para trazabilidad
- **Arquitectura limpia** con separación de capas

## 🛠️ Tecnologías

- **Java 21**
- **Spring Boot 3.5.4**
- **Maven** para gestión de dependencias
- **Lombok** para reducir código boilerplate
- **OpenAPI/Swagger** para documentación
- **SLF4J** para logging

## ⚙️ Requisitos

- **Java 21** o superior
- **Maven 3.6+**
- **IDE** compatible con Java (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/alejandro-vital/test-api-java.git
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

O alternativamente:

```bash
mvn clean package
java -jar target/api-rest-java-example-0.0.1-SNAPSHOT.jar
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

## 🧪 Pruebas con cURL

### Búsqueda exitosa

```bash
curl -X POST http://localhost:8080/api/personas/buscar \
  -H "Content-Type: application/json" \
  -d '{"numeroTelefonico": "5551234567"}'
```

### Búsqueda con número no existente

```bash
curl -X POST http://localhost:8080/api/personas/buscar \
  -H "Content-Type: application/json" \
  -d '{"numeroTelefonico": "5559999999"}'
```

### Número telefónico inválido

```bash
curl -X POST http://localhost:8080/api/personas/buscar \
  -H "Content-Type: application/json" \
  -d '{"numeroTelefonico": "123"}'
```

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

## 🔧 Configuración

### Archivo application.properties

```properties
spring.application.name=API Rest Java Example

# Configuración de OpenAPI/Swagger (opcional)
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
```

### Cambiar el puerto (opcional)

Si necesitas cambiar el puerto por defecto (8080):

```properties
server.port=8090
```

## 🏗️ Arquitectura

El proyecto sigue una **arquitectura hexagonal** con separación clara de responsabilidades:

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

## 🐛 Troubleshooting

### La aplicación no inicia

1. **Verificar Java 21**: `java -version`
2. **Verificar Maven**: `mvn -version`
3. **Limpiar dependencias**: `mvn clean install`

### Puerto 8080 ocupado

```bash
# Ver qué proceso usa el puerto 8080
lsof -i :8080

# Cambiar puerto en application.properties
server.port=8090
```

### Error al ejecutar tests

```bash
# Ejecutar tests con información detallada
mvn test -X
```

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia Apache 2.0. Ver el archivo `LICENSE` para más detalles.

## 📞 Contacto

**Avital**
- Email: contacto@avital.com
- Website: https://avital.com

---

⭐ Si este proyecto te fue útil, ¡no olvides darle una estrella!