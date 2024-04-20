# Documentación Servlet Headers

## Descripción
Este proyecto se centra en la manipulación de cabeceras HTTP en aplicaciones web Java utilizando Servlets. A través de diferentes Servlets, se demuestra cómo gestionar respuestas en formatos HTML, XLS (Excel) y JSON, cómo redirigir y actualizar solicitudes, y cómo manejar sesiones de usuario.

## Requisitos

Para ejecutar y desarrollar este proyecto necesitas tener instalado:

- JDK 17: Necesario para compilar y ejecutar el proyecto Java.
- Apache Maven: Utilizado para gestionar las dependencias del proyecto y facilitar el proceso de construcción.
- Apache Tomcat: Servidor web utilizado para desplegar la aplicación.

## Configuración del Proyecto
La configuración del proyecto se realiza a través del archivo `pom.xml`, el cual contiene las dependencias necesarias para el proyecto y la configuración específica para el plugin de Apache Tomcat, permitiendo el despliegue de la aplicación directamente desde Maven.

### Dependencias
El proyecto depende de la API de Jakarta EE y de la biblioteca Jackson para el manejo de JSON. Estas dependencias se definen en el archivo `pom.xml` como se muestra a continuación:

```xml
<dependencies>
    <dependency>
        <groupId>jakarta.platform</groupId>
        <artifactId>jakarta.jakartaee-api</artifactId>
        <version>9.1.0</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.12.3</version>
    </dependency>
</dependencies>
```

## Configuración de Maven para Tomcat
Para facilitar el despliegue de la aplicación en un servidor Tomcat directamente desde Maven, se incluye la configuración del plugin tomcat7-maven-plugin en el pom.xml. Esta configuración especifica la URL del servidor Tomcat y las credenciales de acceso.

```xml
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <url>http://localhost:8080/manager/text</url>
        <username>admin</username>
        <password>123</password>
    </configuration>
</plugin>
```
  
## Estructura del Proyecto
El proyecto se organiza en varios paquetes y archivos clave, que incluyen:

- **Servlets**: Clases Java que extienden `HttpServlet` y manejan solicitudes HTTP GET o POST.
- **Servicios**: Interfaces y clases que simulan la lógica de negocio y el acceso a datos.
- **Modelos**: Clases que representan entidades o modelos de datos.
- **Vistas**: Archivos HTML para la presentación de datos y formularios de interacción con el usuario.

## Servlets Principales

### ProductosXlsServlet
Este Servlet maneja dos tipos de respuestas dependiendo de la ruta de solicitud: muestra una lista de productos en formato HTML o genera un archivo XLS para descargar.

```java
@WebServlet({"/productos.xls", "/productos"})
public class ProductosXlsServlet extends HttpServlet{
    // Implementación del método doGet
}
```
`Clase` `->` [Servlet de productos](https://github.com/davidfer1112/servlet-headers/blob/main/src/main/java/org/example/controllers/ProductosXlsServlet.java).


## ProductoJsonServlet
Maneja solicitudes para obtener la lista de productos en formato JSON o para recibir detalles de un producto específico en formato JSON mediante una solicitud POST.

```java
@WebServlet("/productos.json")
public class ProductoJsonServlet extends HttpServlet{
    // Implementación de los métodos doGet y doPost
}
```
`Clase` `->` [Servlet Json de productos](https://github.com/davidfer1112/servlet-headers/blob/main/src/main/java/org/example/controllers/ProductoJsonServlet.java).

## HoraActualizadaServlet
Envía una respuesta HTML que se actualiza automáticamente cada segundo para mostrar la hora actual, demostrando el uso de la cabecera refresh.

```java
public class HoraActualizadaServlet extends HttpServlet{
    // Implementación del método doGet
}
```

`Clase` `->` [Servlet hora actual](https://github.com/davidfer1112/servlet-headers/blob/main/src/main/java/org/example/controllers/HoraActualizadaServlet.java).


## Vistas
El proyecto incluye varias páginas HTML que sirven como interfaz de usuario para interactuar con los Servlets, como:

- index.html: Página principal con enlaces a las diferentes funcionalidades.
- login.html: Formulario de inicio de sesión.
- buscar.html: Formulario para buscar productos por nombre.

## Ejecución del Proyecto
Para ejecutar el proyecto, utiliza el siguiente comando de Maven, el cual compilará el proyecto y lo desplegará en el servidor Tomcat configurado:

```cmd
mvn tomcat7:deploy
```
