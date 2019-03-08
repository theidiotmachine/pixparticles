package pixparticles.renderer.generator

import pixparticles.math.StatefulRandom
import pixparticles.renderer.particle.{BaseParticle, BaseParticle2D}

/**
  * Created by tim on 08/05/17.
  */
trait ParticleGenerator[Particle <: BaseParticle[Particle]] {
  def initParticles(random: StatefulRandom): Array[Particle]
}
