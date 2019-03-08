package pixparticles.renderer.particle

import pixparticles.gfx.{Canvas, Draw2D}
import pixparticles.math.{StatefulRandom, Vec2DD}
import pixparticles.palettes.ColorScale

/**
  * Created by tim on 04/05/17.
  */
case class SparkParticle2D(loc: Vec2DD, prevLoc: Vec2DD, vel: Vec2DD, acc: Vec2DD,
                           heat: Double, heatDecay: Double, heatDecayDecay: Double,
                           colorScale: ColorScale)extends Particle2D with DynamicParticleNoRotate[Vec2DD] with CoolingParticle {
  override def evolve(frameTime: Double, random: StatefulRandom): Array[Particle2D] = {
    val (newHeat, newHeatDecay) = evolveHeat(frameTime)
    if(newHeat < 0) {
      Array()
    } else {
      val (newPos, newVel) = evolvePhysicsNoRotate(frameTime)
      Array(SparkParticle2D(newPos, loc, newVel, acc, newHeat, newHeatDecay, heatDecayDecay, colorScale))
    }
  }

  def render(origin: Vec2DD, canvas: Canvas, frame: Int): Unit = {
    val op1 = Draw2D.transform(loc, origin, canvas, frame)
    val op2 = Draw2D.transform(prevLoc, origin, canvas, frame)
    //crudest clipping evah
    (op1, op2) match {
      case (Some(p1), Some(p2)) => Draw2D.drawLine(p2, p1, colorScale.color(heat), canvas, frame)
      case _ =>
    }
  }
}
