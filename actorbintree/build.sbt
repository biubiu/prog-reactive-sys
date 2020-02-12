course := "reactive"
assignment := "actorbintree"

testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v", "-s")
parallelExecution in Test := false

val akkaVersion = "2.6.0"

scalaVersion := "2.13.1"

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-Xlint",
)

libraryDependencies ++= Seq(
  "com.typesafe.akka"        %% "akka-actor"         % akkaVersion,
  "com.typesafe.akka"        %% "akka-testkit"       % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.novocode"             % "junit-interface"     % "0.11"      % Test,
  "org.scalatest"           %% "scalatest"          % "3.0.8" % Test,
  "com.ning"                 % "async-http-client"   % "1.9.40",
  "org.jsoup"               % "jsoup" % "1.8.1",
  "ch.qos.logback"          % "logback-classic"       % "1.1.4"
)
