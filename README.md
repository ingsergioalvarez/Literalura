* Uso

La aplicación consta de una interfaz de línea de comandos (CLI) que te permite interactuar con las funcionalidades disponibles. Al ejecutar la aplicación, verás un menú con las siguientes opciones:

Guardar un nuevo libro: Permite guardar un libro buscando por título en una base de datos local.

Mostrar todos los libros: Muestra todos los libros guardados en la base de datos local.

Buscar libros por título: Busca libros por título y muestra los resultados.

Buscar libros por lenguaje: Busca libros por lenguaje y muestra los resultados.

Mostrar top 10 libros por descargas: Muestra los 10 libros más descargados.

Salir: Sale de la aplicación.

Selecciona una opción ingresando el número correspondiente y sigue las instrucciones que aparecen en pantalla.

* Contribución
¡Las contribuciones son bienvenidas! Si encuentras errores o mejoras que puedan realizarse, no dudes en abrir un issue o enviar un pull request.


Haz un fork del proyecto.
Crea una nueva rama (git checkout -b feature/mejora).
Realiza tus cambios y haz commit de ellos (git commit -am 'Agrega nueva funcionalidad').
Sube tu rama (git push origin feature/mejora).
Abre un pull request.
Licencia

Para crear la base, el desarrollo esta basado en Postgres
Para crear la conexion a la base de datos actualiza en application.porperties tu cadenas de conexion y schema

server.port=9090

spring.datasource.url=jdbc:postgresql://localhost:5432/literaallura
spring.datasource.username=postgres
spring.datasource.password=Root
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update

* Contacto

Para cualquier pregunta o comentario, no dudes en ponerte en contacto a ingsergioalvarez@hotmail.com.
