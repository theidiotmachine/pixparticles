package pixparticles.renderer

import pixparticles.gfx.{Camera, Canvas, ZBuffer}
import pixparticles.math.{StatefulRandom, Vec2DD, Vec3DD}
import pixparticles.renderer.particle.{BaseParticle2D, BaseParticle3D}

/**
  * Created by tim on 11/05/17.
  */
abstract class ParticleRenderer3D[Particle <: BaseParticle3D[Particle]]() extends Renderer3D {
  def initParticles(numFrames: Int, random: StatefulRandom): Array[Particle]

  protected [this] def renderParticles(particles: Array[Particle], camera: Camera, canvas: ZBuffer, frame: Int): Unit = {
    particles.foreach(
      p=>{p.render(Vec3DD(0, 0, 0), camera, canvas, frame)}
    )
  }

  def render(canvas: ZBuffer, camera: Camera, numFrames: Int, frameTime: Double, random: StatefulRandom): Unit = {
    var particles = initParticles(numFrames, random)

    var frame = 0
    while(frame < numFrames){
      renderParticles(particles, camera, canvas, frame)
      particles = evolveParticles(particles, frameTime, random)

      frame += 1
    }
  }

  protected [this] def evolveParticles(particles: Array[Particle], frameTime: Double, random: StatefulRandom): Array[Particle]
}
