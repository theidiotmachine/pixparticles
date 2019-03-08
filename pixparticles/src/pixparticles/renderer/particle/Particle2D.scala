package pixparticles.renderer.particle

import java.awt.image.BufferedImage

import pixparticles.gfx.{Canvas, Draw2D}
import pixparticles.math.{StatefulRandom, Vec2DD, Vec2DI}
import pixparticles.palettes.Color

/**
  * Created by tim on 29/04/17.
  */
trait Particle2D extends BaseParticle2D[Particle2D]{
  def loc: Vec2DD

  protected [this] def drawPixel(loc: Vec2DD, origin: Vec2DD, color: Color, canvas: Canvas, frame: Int): Unit = {
    val renderLoc = Draw2D.transform(loc, origin, canvas, frame)
    renderLoc.foreach(rl=>Draw2D.drawPixelNoClip(rl, canvas.bi, color))
  }
}
