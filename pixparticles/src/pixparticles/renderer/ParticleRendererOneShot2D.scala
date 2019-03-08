package pixparticles.renderer

import pixparticles.math.StatefulRandom
import pixparticles.renderer.generator.ParticleGenerator
import pixparticles.renderer.particle.Particle2D

/**
  * Created by tim on 08/05/17.
  */
class ParticleRendererOneShot2D(generator: ParticleGenerator[Particle2D]) extends ParticleRenderer2D[Particle2D](){

  override def initParticles(numFrames: Int, random: StatefulRandom): Array[Particle2D] = generator.initParticles(random)

  protected [this] def evolveParticles(particles: Array[Particle2D], frameTime: Double, random: StatefulRandom): Array[Particle2D] = {
    val newParticles = particles.flatMap(p=>{p.evolve(frameTime, random)})
    newParticles
  }
}
