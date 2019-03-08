package pixparticles.renderer.particle

import pixparticles.gfx.Canvas
import pixparticles.math.{StatefulRandom, Vec2DD}

/**
  * Created by tim on 11/05/17.
  */
trait BaseParticle[P] {
  type V
  def evolve(frameTime: Double, random: StatefulRandom): Array[P]

}
