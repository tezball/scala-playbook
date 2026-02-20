val scala3Version = "3.3.4"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "scala-playbook",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      guice,
      "org.playframework" %% "play-slick" % "6.1.1",
      "org.playframework" %% "play-slick-evolutions" % "6.1.1",
      "org.postgresql" % "postgresql" % "42.7.4",
      "org.apache.kafka" % "kafka-clients" % "3.9.0"
    )
  )
