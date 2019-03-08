package pixparticles.renderer.generator

import pixparticles.math.{StatefulRandom, Vec2DD}
import pixparticles.palettes.ColorScale
import pixparticles.renderer.particle.{Particle2D, SmokingParticle2D, SparkleParticle2D}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by tim on 08/05/17.
  */
class BurningBitsGenerator2D(val numParts: Int, emberColorScale: ColorScale, smokeColorScale: ColorScale) extends ParticleGenerator[Particle2D]{
  override def initParticles(random: StatefulRandom): Array[Particle2D]= {
    var i = 0
    val newParticles = ArrayBuffer[Particle2D]()
    while(i < numParts){
      val baseLoc = Vec2DD.randCirclePoint(1, random)
      newParticles += SmokingParticle2D(
        SparkleParticle2D(
          (baseLoc * 0.001) + 0.5,
          Vec2DD(baseLoc.x * (3.5 + random.nextDouble()), baseLoc.y * (3.5 + random.nextDouble())),
          Vec2DD(0, 10),
          Math.min(0.85 + random.nextDouble() * 0.25, 1.0),
          -9 + -2.0 * random.nextDouble(),
          -0.75 * random.nextDouble(),
          emberColorScale),
        smokeColorScale)
      i += 1
    }
    newParticles.toArray
  }
}
