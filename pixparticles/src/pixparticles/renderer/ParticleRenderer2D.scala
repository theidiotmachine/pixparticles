package pixparticles.renderer

import pixparticles.gfx.Canvas
import pixparticles.math.{StatefulRandom, Vec2DD}
import pixparticles.renderer.particle.BaseParticle2D

/**
  * A 2d non-looping pixparticles.renderer
  */
abstract class ParticleRenderer2D[Particle <: BaseParticle2D[Particle]]() extends Renderer2D{

  def initParticles(numFrames: Int, random: StatefulRandom): Array[Particle]

  protected [this] def renderParticles(particles: Array[Particle], canvas: Canvas, frame: Int): Unit = {
    particles.foreach(
      p=>{p.render(Vec2DD(0, 0), canvas, frame)}
    )
  }

  def render(canvas: Canvas, numFrames: Int, frameTime: Double, random: StatefulRandom): Unit = {
    var particles = initParticles(numFrames, random)

    var frame = 0
    while(frame < numFrames){
      renderParticles(particles, canvas, frame)
      particles = evolveParticles(particles, frameTime, random)

      frame += 1
    }
  }

  protected [this] def evolveParticles(particles: Array[Particle], frameTime: Double, random: StatefulRandom): Array[Particle]
}
