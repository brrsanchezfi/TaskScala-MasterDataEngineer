# AirportApp

Aplicación desarrollada en **Scala** para el procesamiento de información de vuelos.

---

## 📋 Requisitos

Para ejecutar el proyecto es necesario contar con:

* **Java JDK 8** (Temurin / Amazon Corretto / OpenJDK 8)
* **Scala 2.13.15**
* **sbt** (recomendado versión 1.x)
* Sistema operativo Windows, Linux o macOS

---

## ⚙️ Configuración

Antes de ejecutar el proyecto, asegúrese de que la variable de entorno `JAVA_HOME` apunte a un **JDK 8** y que esté agregado al `PATH`.

Ejemplo:

```
JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-8.0.472.8-hotspot
```

---

## ▶️ Ejecución del programa

Desde la raíz del proyecto (donde se encuentra `build.sbt`):

```bash
sbt run
```

Para generar el JAR ejecutable:

```bash
sbt clean assembly
```

El archivo se generará en:

```
target/scala-2.13/AirportApp.jar
```

Y puede ejecutarse con:

```bash
java -jar target/scala-2.13/AirportApp.jar
```

---

## 📂 Carpeta `output`

En la ruta del programa se agregó una carpeta llamada ``.

* Esta carpeta se utiliza para **almacenar los archivos **********************************``********************************** generados por la aplicación**.
* Si la carpeta no existe, debe crearse antes de ejecutar el programa.
* Los archivos generados se guardarán automáticamente en esta ubicación.

Estructura esperada:

```
AirportApp/
├── build.sbt
├── src/
│   ├── main/
│   │   ├── resources/
│   │   │   └── application.conf
│   │   └── scala/
│   │       └── data/
│   │           ├── Airport.scala
│   │           ├── FileUtils.scala
│   │           ├── Flight.scala
│   │           ├── FlightDate.scala
│   │           ├── FlightsFileReport.scala
│   │           ├── FlightsLoaderConfig.scala
│   │           ├── Row.scala
│   │           ├── Time.scala
│   │           └── FlightsLoaderApp.scala
│   └── test/
│       ├── resources/
│       │   └── test_data.csv
│       └── scala/
│           └── data/
│               ├── FileUtilsTest.scala
│               ├── FlightDateTest.scala
│               ├── FlightTest.scala
│               ├── RowTest.scala
│               ├── TestData.scala
│               └── TimeTest.scala
├── output/
│   ├── archivo1.obj
│   └── archivo2.obj
└── target/
```

---

## 🧪 Tests

Para ejecutar los tests:

```bash
sbt test
```

---

## ℹ️ Notas

* El proyecto está configurado para ser compatible con **Java 8**.
* Se recomienda asignar más memoria a la JVM si se procesan grandes volúmenes de datos.

---

## ✍️ Autor

Proyecto académico / técnico desarrollado en Scala.
