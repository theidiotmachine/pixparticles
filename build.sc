// build.sc
import mill._, scalalib._

object pixparticles extends ScalaModule {
  def scalaVersion = "2.12.8"
  def scalacOptions = Seq("-feature", "-deprecation", "-unchecked")
  def mainClass = Some("pixparticles.PixParticles")

  object test extends Tests {
    def ivyDeps = Agg(ivy"com.lihaoyi::utest:0.6.0")
    def testFrameworks = Seq("utest.runner.Framework")
  }
}

