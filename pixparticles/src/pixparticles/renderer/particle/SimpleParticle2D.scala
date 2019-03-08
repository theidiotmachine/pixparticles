package pixparticles.renderer.particle

import java.awt.image.BufferedImage

import pixparticles.gfx.Canvas
import pixparticles.math.{StatefulRandom, Vec2DD}
import pixparticles.palettes.{Color, ColorScale}

/**
  * Created by tim on 29/04/17.
  */
case class SimpleParticle2D(loc: Vec2DD, vel: Vec2DD, acc: Vec2DD,
                       heat: Double, heatDecay: Double, heatDecayDecay: Double,
                       colorScale: ColorScale) extends Particle2D with DynamicParticleNoRotate[Vec2DD] with CoolingParticle{
  def color: Color = {
    colorScale.color(heat)
  }

  override def evolve(frameTime: Double, random: StatefulRandom): Array[Particle2D] = {
    val (newHeat, newHeatDecay) = evolveHeat(frameTime)
    if(newHeat < 0) {
      Array()
    } else {
      val (newPos, newVel) = evolvePhysicsNoRotate(frameTime)
      Array(SimpleParticle2D(newPos, newVel, acc, newHeat, newHeatDecay, heatDecayDecay, colorScale))
    }
  }

  def render(origin: Vec2DD, canvas: Canvas, frame: Int): Unit = {
    drawPixel(loc, origin, color, canvas, frame)
  }
}
