# AirportApp

Tarea programacion en Scala
---

## 📋 Requisitos

Para ejecutar el proyecto es necesario contar con:

* **Java JDK 8** (Temurin)
* **Scala 2.13.15**
* **sbt** (versión 1.9)

---

## 📂 Carpeta `output`

En la ruta del programa se agregó una carpeta llamada **`output/`**.

* Esta carpeta se utiliza para **almacenar los archivos `.obj` generados por la aplicación**.
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
│   │       │    ├── Airport.scala
│   │       │    ├── FileUtils.scala
│   │       │    ├── Flight.scala
│   │       │    ├── FlightDate.scala
│   │       │    ├── FlightsFileReport.scala
│   │       │    ├── FlightsLoaderConfig.scala
│   │       │    ├── Row.scala
│   │       │    └── Time.scala
│   │       └── FlightsLoaderApp.scala
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

## ✍️ Estudiante

Roberto Sánchez Figueroa
