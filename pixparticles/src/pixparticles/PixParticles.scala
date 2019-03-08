package pixparticles

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import pixparticles.gfx.Canvas
import pixparticles.math.{StatefulRandom, Vec2DD, Vec2DI}
import pixparticles.palettes.LPC
import pixparticles.renderer._
import pixparticles.renderer.generator._

/**
  * Created by tim on 27/04/17.
  */
object PixParticles {

  def oneShot(): Unit = {
    val width = 32
    val height = 32
    val numFrames = 10
    val frameTime = 0.01

    val bufferedImage = new BufferedImage(width * numFrames, height, BufferedImage.TYPE_INT_ARGB)
    val canvas = Canvas(bufferedImage, Vec2DI(width, height), Vec2DI(width, height), numFrames, numFrames)

    //val bang2D = new SimpleBang2D(0.01, 500, OGA.colorScale(0))
    //val bang2D = new DonutBang2D(0.01, 750, OGA.colorScale(0))
    //val bang2D = new SimpleSparkles2D(0.01, 50, OGA.colorScale(16))
    //val bang2D = new Bang2D(0.01, 250, OGA.colorScale(24), OGA.colorScale(24))
    //val bang2D = new GroupedBang2D(0.01, 32, OGA.colorScale(24))
    //val bang2D = new SmokePuff2D(0.01, 64, OGA.colorScale(24))
    //val bang2D = new BurningBits2D(0.01, 16, LPC.colorScale(24),LPC.colorScale(24))
    //val bang2D = new Sparks2D(0.01, 16, LPC.colorScale(16))
    val bang2D = new Lightning2D(4, Array(LPC.colorScale(24).color(6), LPC.colorScale(16).color(3)))
    bang2D.render(canvas, numFrames, frameTime, new StatefulRandom(90))
    val f = new File("./lightning-3.png")
    ImageIO.write(bufferedImage, "png", f)
  }

  def loop(): Unit = {
    val width = 32
    val height = 32
    val numAnimFrames = 8
    val numCanvasFrames = numAnimFrames * 3
    val frameTime = 0.01

    val bufferedImage = new BufferedImage(width * numCanvasFrames, height, BufferedImage.TYPE_INT_ARGB)
    val canvas = Canvas(bufferedImage, Vec2DI(width, height), Vec2DI(width, height), numAnimFrames, numCanvasFrames)

    //val bang2D = new ParticleRendererLoop2D(new BangGenerator(250, LPC.colorScale(24), LPC.colorScale(24)))
    //val bang2D = new ParticleRendererLoop2D(new SparkGenerator(32, LPC.colorScale(24)))
    //val bang2D = new ParticleRendererLoop2D(new SmokePuffGenerator(100, LPC.colorScale(24)))
    //val bang2D = new ParticleRendererLoop2D(new BurningBitsGenerator(8, LPC.colorScale(24), LPC.colorScale(24)))
    //val bang2D = new ParticleRendererLoop2D(new SimpleSparklesGenerator(24, LPC.colorScale(24)))
    //val bang2D = new ParticleRendererLoop2D(new FlameGenerator(50, LPC.colorScale(24), Vec2DD(0.02, 0.02)))
    //val bang2D = new ParticleRendererLoop2D(new FlameGenerator(200, LPC.colorScale(24), Vec2DD(0.1, 0.05), Vec2DD(0, -3)))
    val bang2D = new ParticleRendererLoop2D(new FlameGenerator2D(600, LPC.colorScale(24), Vec2DD(0.2, 0.1), Vec2DD(0, -4)))
    bang2D.render(canvas, numAnimFrames, frameTime, new StatefulRandom(901234))
    val f = new File("./largeflames-2.png")
    ImageIO.write(bufferedImage, "png", f)
  }

  def main(args: Array[String]): Unit = {
    loop()
  }
}
