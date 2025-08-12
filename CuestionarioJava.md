# Cuestionario Java - Programación Orientada a Objetos y Microservicios

## Sección 1. Programación Orientada a Objetos

### 1. Al compilar un archivo .java, ¿Qué tipo de archivo se genera?

Al compilar un archivo .java, se genera un archivo llamado .class que es el archivo que contiene el bytecode de la aplicacion y la JVM lo interpreta y lo ejecuta

### 2. ¿Cuál es la función principal de la memoria heap?

Es la memoria dinamica, donde se almacenan los objetos en tiempo de ejecucion y que es gestionada por el recolector de basura (Garbage Collector)

### 3. ¿Cuál es la función principal de la memoria stack?

Almacena las variables locales, funciona como una pila FIFO, y se utiliza para el almacenamiento temporal de datos y se limpia automaticamente cuando un método termina su ejecución

### 4. Estructura de una clase en Java:

```java
// Declaración de la clase
public class NombreClase {
    // Atributos
    private tipo atributo1;
    protected tipo atributo2;
    
    // Constructor
    public NombreClase(tipo parametro) {
        this.atributo1 = parametro;
    }

    // Métodos getters
    public tipo getAtributo1() {
        return atributo1;
    }

    public void setAtributo1(tipo valor) {
        this.atributo1 = valor;
    }

    // Otros métodos
    public void metodo1() {
        // Implementación del método 1
    }
}
```

### 5. ¿Para qué sirve Lombok?

Es una libreria que se usa para reducir codigo mediante anotaciones, generando automaticamente getters, setters, constructores, y otros metodos comunes en las clases

### 6. ¿Qué entiendes por Microservicio?

Es un estilo arquitectónico de software donde una aplicación se divide en servicios pequeños, independientes y autónomos que se comunican entre sí, generalmente cada servicio tiene su propia base de datos, y existe un api gateway que gestiona las peticiones y reedirige las peticiones al servicio correspondiente

### 7. ¿Qué es polimorfismo?

Es un principio de la POO que permite que un mismo metodo se pueda comportar de diferentes maneras según el objeto que lo invoque, por ejemplo un mentodo puede comportarse diferente segun los parametros que reciba o si se sobreescribio con Override

### 8. ¿Qué es herencia?

Otro principio de la POO que permite que una clase hija herede atributos y métodos de una clase padre, facilitando la reutilización de código

## Sección 2. Microservicios, contenedores y herramientas de desarrollo

### 9. ¿Que es REST?

Es un estilo de arquitectura para el desarrollo de servicios web, basado en el uso de HTTP y operaciones como GET, POST, PUT, DELETE

### 10. ¿Que es RestFull?

RESTful es la implementación práctica de las API siguiendo los principios de REST

### 11. ¿Que es maven?

Maven es una herramienta de gestión y construcción de proyectos Java que automatiza la compilación, maneja dependencias, ejecuta pruebas y empaqueta aplicaciones usando un archivo POM

### 12. Menciona algunos de los comandos comunes que conozcas para maven.

- `mvn compile` - Compila el codigo
- `mvn test` - Ejecuta las pruebas
- `mvn package` - Empaqueta el proyecto
- `mvn install` - Instala el proyecto en repositorio local
- `mvn clean` - Limpia archivos generados
- `mvn spring-boot:run` - Ejecuta aplicación Spring Boot

### 13. Para agregar una nueva dependencia a un proyecto, ya sea usando maven se sigue una estructura; Lista los elementos que se deben especificar.

En el archivo pom.xml en la sección `<dependencies>` se agrega lo siguiente:

```xml
<dependency>
    <groupId>com.ejemplo</groupId>
    <artifactId>mi-dependencia</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 14. ¿Que es Rancher?

Rancher es una plataforma de gestión de contenedores que proporciona una interfaz web para administrar aplicaciones facilitando el despliegue y monitoreo

### 15. ¿Que es sonarQube?

Es una plataforma para análisis de código que detecta bugs, vulnerabilidades de seguridad para mantener la calidad del código

### 16. Principales comandos de gitlab y que hacen cada uno?

- `git clone` - Clona un repositorio
- `git add` - Añade archivos al staging area
- `git commit` - Confirma cambios
- `git push` - Sube cambios al repositorio
- `git pull` - Descarga y fusiona cambios
- `git branch` - Gestiona ramas
- `git merge` - Fusiona ramas
- `git checkout` - Cambia de rama

### 17. Menciona los comandos principales para crear imágenes en un servidor docker?

- `docker build` - Crea una imagen a partir de un Dockerfile
- `docker images` - Lista las imágenes disponibles
- `docker rmi` - Elimina una imagen
- `docker run` - Ejecuta un contenedor

### 18. Que entiendes por contenedor?

Los contenedores son enornos ligeros que incluyen los archivos necesarios para ejecutar una aplicación lo que permite que la aplicación se ejecute de manera facil y rapida en cualquier sistema que tenga Docker instalado

### 19. ¿Que entiendes por patrones de diseño?

Es una manera de resolver problemas comunes en el desarrollo, proporcionan modelos reutilizables que pueden ser aplicados a diferentes situaciones ayudando a mejorar la calidad del software, de estructurar el codigofacilitando su desarrollo

### 20. Lista las anotaciones de spring que más utilises y que hace cada una?

- `@RestController` - Define un controlador REST
- `@RequestMapping/@GetMapping/@PostMapping` - Mapea URLs a métodos
- `@Autowired` - Inyección de dependencias automática
- `@Service` - Marca una clase como servicio de negocio
- `@Repository` - Marca una clase como repositorio de datos
- `@Component` - Marca una clase como componente Spring
- `@Configuration` - Define clase de configuración
- `@RequestBody/@ResponseBody` - Maneja cuerpo de petición/respuesta
- `@PathVariable` - Extrae variables de la URL
- `@RequestParam` - Extrae parámetros de consulta