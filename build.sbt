name := "multivac-fakenews"

version := "0.1"

scalaVersion := "2.11.12"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

resolvers ++= Seq(
  "JBoss Repository" at "http://repository.jboss.org/nexus/content/repositories/releases/",
//  "Spray Repository" at "http://repo.spray.cc/",
  "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
  "Akka Repository" at "http://repo.akka.io/releases/",
  "Twitter4J Repository" at "http://twitter4j.org/maven2/",
  "Apache HBase" at "https://repository.apache.org/content/repositories/releases",
  "Twitter Maven Repo" at "http://maven.twttr.com/",
  "scala-tools" at "https://oss.sonatype.org/content/groups/scala-tools",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Second Typesafe repo" at "http://repo.typesafe.com/typesafe/maven-releases/",
  "Mesosphere Public Repository" at "http://downloads.mesosphere.io/maven",
  "Apache Repository" at "https://repository.apache.org/content/repositories/releases/",
  "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
  Resolver.sonatypeRepo("public")
)

libraryDependencies ++= {
  val sparkVer = "2.2.0"
  Seq(
    "org.apache.spark" %% "spark-core" % sparkVer % "provided" withSources(),
    "org.apache.spark" %% "spark-sql" % sparkVer,
    "org.apache.spark" %% "spark-streaming" % sparkVer % "provided" withSources(),
    "org.apache.spark" %% "spark-mllib" %sparkVer % "provided" withSources(),
    "org.apache.spark" %% "spark-hive" % sparkVer,
    "org.apache.spark" %% "spark-graphx" % sparkVer % "provided" withSources(),
    "org.apache.spark" %% "spark-yarn" % sparkVer % "provided" withSources(),
    "com.typesafe" % "config" % "1.3.2"
  )
}

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

assemblyExcludedJars in assembly := {
  val cp = (fullClasspath in assembly).value
  cp filter {
    j => {
      j.data.getName.startsWith("spark-core") ||
        j.data.getName.startsWith("spark-sql") ||
        j.data.getName.startsWith("spark-hive") ||
        j.data.getName.startsWith("spark-mllib") ||
        j.data.getName.startsWith("spark-graphx") ||
        j.data.getName.startsWith("spark-yarn") ||
        j.data.getName.startsWith("spark-streaming") ||
        j.data.getName.startsWith("hadoop")
    }
  }
}