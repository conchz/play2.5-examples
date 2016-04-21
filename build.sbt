import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import play.sbt.PlayScala

name := """play2.5-examples"""

organization in ThisBuild := "com.github.lavenderx"

version := "0.1.0"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, JavaAppPackaging)

scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-unchecked"
)

javacOptions ++= Seq(
  "-source", "1.8",
  "-target", "1.8",
  "-Xlint:unchecked"
)

ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}

resolvers := Seq(
  Resolver.mavenLocal,
  "gtan repox" at "http://repox.gtan.com:8078/",
  "maven repo" at "https://repo1.maven.org/maven2/",
  "typesafe repo" at "https://dl.bintray.com/typesafe/maven-releases/",
  "sbt-plugin repo" at "https://dl.bintray.com/sbt/sbt-plugin-releases/"
)

libraryDependencies ++= {
  Seq(
    filters,
    cache,
    ws,
    "org.abstractj.kalium" % "kalium" % "0.4.0",
    "org.webjars" % "requirejs" % "2.2.0",
    "org.webjars" % "underscorejs" % "1.8.3",
    "org.webjars" % "jquery" % "1.12.3",
    "org.webjars" % "bootstrap" % "3.3.6" exclude("org.webjars", "jquery"),
    "org.webjars" % "angularjs" % "1.5.5" exclude("org.webjars", "jquery"),
    "org.webjars.bower" % "angular-ui-grid" % "3.1.1"
  )
}

routesGenerator := InjectedRoutesGenerator

pipelineStages := Seq(rjs, digest, gzip)
RjsKeys.paths += ("jsRoutes" -> ("/jsroutes" -> "empty:"))
