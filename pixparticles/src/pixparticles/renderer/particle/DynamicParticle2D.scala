package pixparticles.renderer.particle

import pixparticles.math.{Angle, Vec2DD}

/**
  * Created by tim on 02/05/17.
  */
trait DynamicParticle2D extends DynamicParticleNoRotate[Vec2DD]{
  val angle: Angle
  val radialVelocity: Double

  protected [this] def evolvePhysics(frameTime: Double): (Vec2DD, Vec2DD, Angle) = {
    val newVel = vel + acc * frameTime
    val newPos = loc + newVel * frameTime
    val newAngle = angle + Angle(radialVelocity * frameTime)
    (newPos, newVel, newAngle)
  }
}
