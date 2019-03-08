package pixparticles.renderer.particle

import pixparticles.math.{Vec2DD, VecD}

/**
  * Created by tim on 02/05/17.
  */
trait DynamicParticleNoRotate[V <: VecD[V]] {
  val loc: V
  val vel: V
  val acc: V

  protected [this] def evolvePhysicsNoRotate(frameTime: Double): (V, V) = {
    val newVel = vel + acc * frameTime
    val newPos = loc + newVel * frameTime
    (newPos, newVel)
  }
}
