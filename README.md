# Guía para Crear el Archivo JAR y Configurar un Servicio en Linux

Este documento proporciona instrucciones para generar un archivo JAR ejecutable de su proyecto Spring Boot utilizando IntelliJ IDEA y para configurar este JAR como un servicio en un sistema Linux utilizando `systemd`.

## Generación del Archivo JAR con IntelliJ IDEA

Para empaquetar su proyecto Spring Boot en un archivo JAR ejecutable en IntelliJ IDEA, siga estos pasos:

1. **Abrir el Proyecto**: Inicie IntelliJ IDEA y abra su proyecto existente.

2. **Acceder a la Estructura del Proyecto**:
   - Navegue al menú `File` y seleccione `Project Structure`.

3. **JDK**:
   - Seleccionar la versión de SDK `Java 17`
   - Aplicar y aceptar cambios
4. **Construir el Artefacto**:
   - Simplemente ejecutar el proyecto
5. **Generar el archivo .jar**
   -Nos dirijimos a la pestaña de `Gradle` y abrimos Tasks->build y hacemos doble click a `bootJar`
   -El archivo .jar se abrá generado en la ruta `builds/libs`
   
Para ejecutar su aplicación Spring Boot como un servicio en Linux, siga estos pasos:

1. **Mover el Archivo JAR**: Copie el archivo JAR generado a un directorio en su servidor, por ejemplo, `/opt/spring-app/spring-app.jar`.

2. **Crear el Archivo de Servicio**:
   - Abra una terminal y ejecute:
     ```bash
     sudo nano /etc/systemd/system/spring-app.service
     ```
   - En el editor, agregue el siguiente contenido:
     ```ini
     [Unit]
     Description=Spring Boot Application
     After=network.target

     [Service]
     User=springuser
     ExecStart=/usr/bin/java /opt/spring-app/spring-app.jar --spring.config.location=/usr/bin/application.properties
     SuccessExitStatus=143
     Restart=on-failure
     RestartSec=10

     [Install]
     WantedBy=multi-user.target
     ```
   - Reemplace `/usr/bin/java` con la ruta a su ejecutable de Java y `/opt/spring-app/spring-app.jar` con la ruta a su archivo JAR asi como `/usr/bin/application.properties` para las propiedades del programa.
   - Guarde y cierre el archivo.

3. **Recargar systemd y Habilitar el Servicio**:
   - Recargue la configuración de `systemd` para reconocer el nuevo servicio:
     ```bash
     sudo systemctl daemon-reload
     ```
   - Habilite el servicio para que se inicie automáticamente al arrancar el sistema:
     ```bash
     sudo systemctl enable spring-app
     ```

4. **Iniciar el Servicio**:
   - Inicie el servicio manualmente por primera vez:
     ```bash
     sudo systemctl start spring-app
     ```

5. **Verificar el Estado del Servicio**:
   - Para asegurarse de que el servicio esté funcionando correctamente, ejecute:
     ```bash
     sudo systemctl status spring-app
     ```
   - Debería ver un estado activo (running) si todo está configurado correctamente.

Siguiendo estos pasos, podrá empaquetar su aplicación Spring Boot en un archivo JAR ejecutable y configurarla para que se ejecute como un servicio en su sistema Linux, asegurando que se inicie automáticamente y se gestione adecuadamente.
