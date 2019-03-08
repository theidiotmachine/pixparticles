package pixparticles.renderer.particle

import java.awt.image.BufferedImage

import pixparticles.gfx.Canvas
import pixparticles.math.{Angle, StatefulRandom, Vec2DD}

/**
  * Created by tim on 29/04/17.
  */
case class ParticleGroup2D(loc: Vec2DD, vel: Vec2DD, acc: Vec2DD, angle: Angle,
                           radialVelocity: Double, subParticles: Array[Particle2D]) extends Particle2D with DynamicParticle2D{
  override def evolve(frameTime: Double, random: StatefulRandom): Array[Particle2D] = {
    val newSubParticles = subParticles.flatMap(sb=>sb.evolve(frameTime, random))
    if(newSubParticles.isEmpty)
      Array()
    else {
      val (newPos, newVel, newAngle) = evolvePhysics(frameTime)
      Array(ParticleGroup2D(newPos, newVel, acc, newAngle, radialVelocity, newSubParticles))
    }
  }

  override def render(origin: Vec2DD, canvas: Canvas, frame: Int): Unit = {
    subParticles.foreach(sp=>{
      val p = sp.loc
      val s = angle.sin
      val c = angle.cos
      val sbpl = Vec2DD(p.x * c - p.y * s, p.x * s + p.y * c) + loc + origin
      sp.render(sbpl, canvas, frame)
    })
  }
}
