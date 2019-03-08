package pixparticles.math

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import pixparticles.palettes.Color

import scala.util.Random

/**
  * Created by tim on 03/05/17.
  */
object RandomTest {
  def main(args: Array[String]): Unit = {
    val r1 = new StatefulRandom(100)
    var i = 0
    while(i < 10000){
      val int = r1.nextInt(7919)
      assert(int >= 0 && int < 7919)
      i += 1
    }

    val r2 = new StatefulRandom(200)
    i = 0
    while(i < 10000){
      val double = r2.nextDouble()
      assert(double >= 0.0 && double < 1.0)
      i += 1
    }

    val sz = 32
    val biNew = new BufferedImage(sz, sz, BufferedImage.TYPE_INT_RGB)
    val biOld = new BufferedImage(sz, sz, BufferedImage.TYPE_INT_RGB)

    val r3 = new StatefulRandom(300)
    val rControl = new Random
    i = 0
    val c = Color(0, 0, 128)
    while(i < 100000){
      markPixel(sz, biNew, r3.nextDouble(), r3.nextDouble())
      markPixel(sz, biOld, rControl.nextDouble(), rControl.nextDouble())
      i += 1
    }

    val fNew = new File("/home/tim/Code/OGA/tim/Pix/randtestnew.png")
    ImageIO.write(biNew, "png", fNew)

    val fOld = new File("/home/tim/Code/OGA/tim/Pix/randtestold.png")
    ImageIO.write(biOld, "png", fOld)

  }

  private def markPixel(sz: Int, bi: BufferedImage, x: Double, y: Double): Unit = {
    val xi = (x * sz).toInt
    val yi = (y * sz).toInt
    val excol = Color(bi.getRGB(xi, yi))
    val newColor = if (excol.b < 255) Color(0, 0, excol.b + 1) else excol
    bi.setRGB(xi, yi, newColor.toInt)
  }
}
