package pixparticles.renderer.particle

import java.awt.image.BufferedImage

import pixparticles.gfx.{Canvas, Draw2D}
import pixparticles.math.{StatefulRandom, Vec2DD, Vec2DI}
import pixparticles.palettes.ColorScale

/**
  * Created by tim on 29/04/17.
  */
case class SparkleParticle2D(loc: Vec2DD, vel: Vec2DD, acc: Vec2DD,
                             heat: Double, heatDecay: Double, heatDecayDecay: Double,
                             colorScale: ColorScale) extends Particle2D with DynamicParticleNoRotate[Vec2DD] with CoolingParticle{

  override def evolve(frameTime: Double, random: StatefulRandom): Array[Particle2D] = {
    val (newHeat, newHeatDecay) = evolveHeat(frameTime)
    if(newHeat < 0) {
      Array()
    } else {
      val (newPos, newVel) = evolvePhysicsNoRotate(frameTime)
      Array(SparkleParticle2D(newPos, newVel, acc, newHeat, newHeatDecay, heatDecayDecay, colorScale))
    }
  }

  def render(origin: Vec2DD, canvas: Canvas, frame: Int): Unit = {
    val orl = Draw2D.transform(loc, origin, canvas, frame)
    orl.foreach(rl=>{
      Draw2D.drawPixelNoClip(rl, canvas.bi, colorScale.color(heat))
      val oDarkerColor = colorScale.darkerColor(heat)
      oDarkerColor.foreach(dc=>{
        Draw2D.drawPixel(Vec2DI(rl.x + 1, rl.y), dc, canvas, frame)
        Draw2D.drawPixel(Vec2DI(rl.x - 1, rl.y), dc, canvas, frame)
        Draw2D.drawPixel(Vec2DI(rl.x, rl.y + 1), dc, canvas, frame)
        Draw2D.drawPixel(Vec2DI(rl.x, rl.y - 1), dc, canvas, frame)
      })
    })
  }
}
