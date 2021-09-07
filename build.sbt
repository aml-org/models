val nexus: MavenRepository = "MuleSoft releases" at "https://repository-master.mulesoft.org/nexus/content/repositories/releases"

lazy val amfArtifactRef = "com.github.amlorg" %% "amf-aml" % "6.0.0-beta.4" % "test"
lazy val amfSourceRef = {
  val workspaceDirectory: File =
    sys.props.get("sbt.mulesoft") match {
      case Some(x) => file(x)
      case _       => Path.userHome / "mulesoft"
    }
  ProjectRef(workspaceDirectory / "amf-aml", "amlJVM")
}

lazy val models = (project in file(".")).sourceDependency(amfSourceRef, amfArtifactRef)

models / scalaVersion := "2.12.14"
models / resolvers ++= Seq(nexus)
models / libraryDependencies += "org.scalatest"   %% "scalatest"         % "3.0.5" % "test"
models / libraryDependencies += "org.mule.common" %% "scala-common-test" % "0.0.6" % "test"
models / publish := {}
