package pixparticles.renderer.generator

import pixparticles.math.{StatefulRandom, Vec2DD}
import pixparticles.palettes.ColorScale
import pixparticles.renderer.particle.{Particle2D, SparkParticle2D}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by tim on 09/05/17.
  */
class SparkGenerator2D(val numParts: Int, colorScale: ColorScale) extends ParticleGenerator[Particle2D] {
  override def initParticles(random: StatefulRandom): Array[Particle2D]= {
    var i = 0
    val newParticles = ArrayBuffer[Particle2D]()
    while(i < numParts){
      val baseLoc = Vec2DD.randCirclePoint(1, random)
      val loc = (baseLoc * 0.001) + 0.5
      newParticles += SparkParticle2D(
        loc, loc,
        Vec2DD(baseLoc.x * (4.5 + random.nextDouble()), baseLoc.y * (4.5 + random.nextDouble())),
        Vec2DD(0, 35),
        Math.min(0.85 + random.nextDouble() * 0.25, 1.0),
        -9 + -2.0 * random.nextDouble(),
        -0.75 * random.nextDouble(),
        colorScale)
      i += 1
    }
    newParticles.toArray
  }
}
