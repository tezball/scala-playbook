# scala-playbook

A hello world web application built with [Play Framework](https://www.playframework.com/) and Scala 3.

## Prerequisites

- [JDK 11+](https://adoptium.net/)
- [sbt](https://www.scala-sbt.org/download/)

### Installing sbt via Coursier

A Coursier launcher (`cs`) is included in this repo. You can use it to install sbt:

```sh
./cs setup
```

This installs JDK, sbt, and other Scala tools.

## Running

Start the development server:

```sh
sbt run
```

Then open [http://localhost:9000](http://localhost:9000) in your browser to see "Hello, World!".

## Project structure

```
.
├── app/
│   └── controllers/
│       └── HomeController.scala   # Application controller
├── conf/
│   ├── application.conf           # Play configuration
│   └── routes                     # URL routing
├── build.sbt                      # Build definition
└── project/
    ├── build.properties           # sbt version
    └── plugins.sbt                # Play sbt plugin
```
