package pixparticles.renderer.generator

import pixparticles.math.{Angle, StatefulRandom, Vec2DD}
import pixparticles.palettes.ColorScale
import pixparticles.renderer.particle.{Particle2D, ParticleGroup2D, SimpleParticle2D}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by tim on 08/05/17.
  */
class SmokePuffGenerator2D(val numParts: Int, val colorScale: ColorScale) extends ParticleGenerator[Particle2D]{
  def initParticles(random: StatefulRandom): Array[Particle2D] = {
    var i = 0
    val newParticles = ArrayBuffer[Particle2D]()
    while(i < numParts){
      val newSubParticles = ArrayBuffer[Particle2D]()
      val baseLoc = Vec2DD.randCirclePoint(1, random)

      val numSubParticles = 20 + random.nextInt(20)
      val baseDecay = -3.0 * random.nextDouble()
      var j = 0
      while(j < numSubParticles) {
        val subBaseLoc = Vec2DD.randCirclePoint(1, random)

        newSubParticles += SimpleParticle2D(
          subBaseLoc * 0.02,
          Vec2DD(2.5 * (random.nextDouble() - 0.5), 2.5 * (random.nextDouble()-0.5)),
          Vec2DD(0, 0),
          Math.min(random.nextDouble() * 0.5, 0.2),
          -1 + -8 * random.nextDouble() + baseDecay,
          0.0,
          colorScale
        )
        j += 1
      }

      newParticles += ParticleGroup2D(
        Vec2DD(0.5 + baseLoc.x * 0.02, baseLoc.y * 0.02 + 0.9),
        Vec2DD(-4.0 * (random.nextDouble() - 0.5), -2.0 + -5.0 * random.nextDouble()),
        Vec2DD(0, -10 + -10 * random.nextDouble()),
        Angle(0),
        random.nextDouble() * 10,
        newSubParticles.toArray
      )
      i += 1
    }
    newParticles.toArray
  }
}
