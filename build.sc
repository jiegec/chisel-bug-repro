import mill._
import mill.scalalib.publish._
import scalalib._
import scalafmt._
import coursier.maven.MavenRepository

// learned from https://github.com/OpenXiangShan/fudian/blob/main/build.sc
val defaultVersions = Map(
  "chisel" -> ("org.chipsalliance", "5.0.0", false),
  "chisel-plugin" -> ("org.chipsalliance", "5.0.0", true),
  "chiseltest" -> ("edu.berkeley.cs", "5.0.0", false),
  "scalatest" -> ("org.scalatest", "3.2.10", false),
  "spinalhdl-core" -> ("com.github.spinalhdl", "1.8.1", false),
  "spinalhdl-lib" -> ("com.github.spinalhdl", "1.8.1", false),
  "spinalhdl-idsl-plugin" -> ("com.github.spinalhdl", "1.8.1", false)
)

val commonScalaVersion = "2.13.10"

def getVersion(dep: String) = {
  val (org, ver, cross) = defaultVersions(dep)
  val version = sys.env.getOrElse(dep + "Version", ver)
  if (cross)
    ivy"$org:::$dep:$version"
  else
    ivy"$org::$dep:$version"
}

trait CommonModule extends ScalaModule {
  def scalaVersion = commonScalaVersion

  // for snapshot dependencies
  override def repositoriesTask = T.task {
    super.repositoriesTask() ++ Seq(
      MavenRepository("https://oss.sonatype.org/content/repositories/snapshots")
    )
  }

  override def scalacOptions =
    Seq("-deprecation", "-feature", "-language:reflectiveCalls")
}

object `bug-repro` extends CommonModule with ScalafmtModule {
  override def ivyDeps = super.ivyDeps() ++ Agg(
    getVersion("chisel"),
    getVersion("chiseltest"),
    getVersion("spinalhdl-core"),
    getVersion("spinalhdl-lib")
  )

  override def scalacPluginIvyDeps = super.scalacPluginIvyDeps() ++ Agg(
    getVersion("spinalhdl-idsl-plugin"),
    getVersion("chisel-plugin")
  )

  object test extends ScalaTests with TestModule.ScalaTest {
    override def ivyDeps = super.ivyDeps() ++ Agg(
      getVersion("scalatest")
    )
  }
}
