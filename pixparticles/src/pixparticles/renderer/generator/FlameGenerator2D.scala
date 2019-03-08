package pixparticles.renderer.generator

import pixparticles.math.{Angle, StatefulRandom, Vec2DD}
import pixparticles.palettes.ColorScale
import pixparticles.renderer.particle.{Particle2D, ParticleGroup2D, SimpleParticle2D}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by tim on 09/05/17.
  */
class FlameGenerator2D(val numParts: Int, val colorScale: ColorScale,
                       val initSpread: Vec2DD, val initVel: Vec2DD) extends ParticleGenerator[Particle2D] {
  override def initParticles(random: StatefulRandom): Array[Particle2D] = {
    var i = 0
    val newParticles = ArrayBuffer[Particle2D]()
    while(i < numParts){
      val baseLoc = Vec2DD.randCirclePoint(1, random)

      newParticles += SimpleParticle2D(
        Vec2DD(0.5 + baseLoc.x * initSpread.x, baseLoc.y * initSpread.y + 0.9),
        Vec2DD(initVel.x + -4.0 * (random.nextDouble() - 0.5), initVel.y + -5.0 * random.nextDouble()),
        Vec2DD(0, -10 + -10 * random.nextDouble()),
        Math.min(0.85 + random.nextDouble() * 0.25, 1.0),
        -9 + -2.0 * random.nextDouble(),
        -0.75 * random.nextDouble(),
        colorScale
      )
      i += 1
    }
    newParticles.toArray
  }
}
