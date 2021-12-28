val nexus: MavenRepository = "MuleSoft releases" at "https://repository-master.mulesoft.org/nexus/content/repositories/releases"

ThisBuild / scalaVersion := "2.12.14"
ThisBuild / resolvers ++= Seq(nexus)
ThisBuild / libraryDependencies += "com.github.amlorg" %% "amf-aml"           % "6.0.2" % "test"
ThisBuild / libraryDependencies += "org.scalatest"     %% "scalatest"         % "3.0.5"        % "test"
ThisBuild / libraryDependencies += "org.mule.common"   %% "scala-common-test" % "0.0.6"        % "test"
ThisBuild / publish := {}
