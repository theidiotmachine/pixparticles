package pixparticles.renderer

import pixparticles.gfx.Canvas
import pixparticles.math.{StatefulRandom, Vec2DD}
import pixparticles.renderer.generator.ParticleGenerator
import pixparticles.renderer.particle.{LoopParticle2D, Particle2D}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by tim on 09/05/17.
  */
class ParticleRendererLoop2D(generator: ParticleGenerator[Particle2D]) extends ParticleRenderer2D[LoopParticle2D]() {

  def initParticles(numFrames: Int, random: StatefulRandom): Array[LoopParticle2D] = {
    val particles = generator.initParticles(random)
    val loopParticles = ArrayBuffer[LoopParticle2D]()
    particles.foreach(p=>{
      loopParticles += LoopParticle2D(random.nextInt(numFrames), p)
    })
    loopParticles.toArray
  }

  protected [this] def evolveParticles(particles: Array[LoopParticle2D], frameTime: Double, random: StatefulRandom): Array[LoopParticle2D] = {
    val newParticles = particles.flatMap(p=>{p.evolve(frameTime, random)})
    newParticles
  }


}
