version in ThisBuild := "0.0.1"

organization in ThisBuild := "higility"

scalaVersion in ThisBuild := "2.12.8"

triggeredMessage in ThisBuild := Watched.clearWhenTriggered

addCommandAlias("root", "project todo")

addCommandAlias("cd", "project")

addCommandAlias("ls", "projects")

scalacOptions in ThisBuild += "-unchecked"

shellPrompt := (_ => fancyPrompt(name.value))

def fancyPrompt(projectName : String): String =
  s"""|
      |[info] Welcome to the ${cyan(projectName)} project
      |sbt> """.stripMargin

def cyan(projectName: String): String =
  scala.Console.CYAN + projectName + scala.Console.RESET

lazy val entities =
  project
    . in(file("./1-entities"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))

lazy val core =
  project
    . in(file("./2-application-core"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))
    .dependsOn(entities)

lazy val persistence =
  project
    . in(file("./3-persistence-in-memory"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))
    .dependsOn(core)

lazy val delivery =
  project
    . in(file("./3-delivery-terminal"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))
    .dependsOn(core)

lazy val main =
  project
    . in(file("./4-main"))
    .settings(shellPrompt := (_ => fancyPrompt(name.value)))
    .dependsOn(delivery)
    .dependsOn(persistence)
