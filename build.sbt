import AssemblyKeys._

assemblySettings

jarName in assembly := "lambdasloth.todo.jar"

name := "Todo"

organization := "com.lambdasloth"

version := "0.0.1"

scalaVersion := "2.11.7"

val PhantomVersion = "1.22.0"

libraryDependencies ++= Seq(
  "com.websudos"        %% "phantom-dsl"                   % PhantomVersion,
  "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.12.1" % "test" withSources() withJavadoc()
)

resolvers ++= Seq(
  "Typesafe repository snapshots"     at "http://repo.typesafe.com/typesafe/snapshots/",
  "Typesafe repository releases"      at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype repo"                     at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases"                 at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype snapshots"                at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype staging"                  at "http://oss.sonatype.org/content/repositories/staging",
  "Java.net Maven2 Repository"        at "http://download.java.net/maven/2/",
  "Twitter Repository"                at "http://maven.twttr.com",
  "Websudos releases"                 at "https://dl.bintray.com/websudos/oss-releases/"
)

libraryDependencies += "joda-time" % "joda-time" % "2.8.1"
libraryDependencies += "com.typesafe" % "config" % "1.3.0"

initialCommands := "import com.lambdasloth.todo._"

val meta = """META.INF(.)*""".r

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) => {
    case PathList("javax", "servlet", xs@_*) => MergeStrategy.first
    case PathList(ps@_*) if ps.last endsWith ".html" => MergeStrategy.first
    case "application.conf" => MergeStrategy.concat
    case meta(_) => MergeStrategy.discard
    case x => old(x)
  }
}
