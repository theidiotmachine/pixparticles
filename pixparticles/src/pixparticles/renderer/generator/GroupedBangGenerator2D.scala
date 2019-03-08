package pixparticles.renderer.generator

import pixparticles.math.{Angle, StatefulRandom, Vec2DD}
import pixparticles.palettes.ColorScale
import pixparticles.renderer.particle.{Particle2D, ParticleGroup2D, SimpleParticle2D}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by tim on 09/05/17.
  */
class GroupedBangGenerator2D(val numParts: Int, colorScale: ColorScale) extends ParticleGenerator[Particle2D]{
  override def initParticles(random: StatefulRandom): Array[Particle2D] = {
    var i = 0
    val newParticles = ArrayBuffer[Particle2D]()
    while(i < numParts){
      val newSubParticles = ArrayBuffer[Particle2D]()
      val baseLoc = Vec2DD.randCirclePoint(1, random)
      /*
            val numSubParticles = 10 + random.nextInt(10)
            val baseDecay = -5.0 * random.nextDouble()
            var j = 0
            while(j < numSubParticles) {
              val subBaseLoc = Vec2DD.randCirclePoint(1, random)
              val distFromBaseLoc = subBaseLoc.len

              newSubParticles += SimpleParticle2D(
                subBaseLoc * 0.01,
                Vec2DD(subBaseLoc.x * (0.1 + random.nextDouble()), subBaseLoc.y * (0.1 + random.nextDouble())),
                Vec2DD(0, 0),
                Math.min(0.85 + random.nextDouble() * 0.25, 1.0),
                -8 * random.nextDouble() + -10.0 * distFromBaseLoc + baseDecay,
                -0.75 * random.nextDouble(),
                colorScale
              )
              j += 1
            }
      */

      val numSubParticles = 10 + random.nextInt(20)
      val baseDecay = -5.0 * random.nextDouble()
      var j = 0
      while(j < numSubParticles) {
        val subBaseLoc = Vec2DD.randCirclePoint(1, random)
        val distFromBaseLoc = subBaseLoc.len

        newSubParticles += SimpleParticle2D(
          subBaseLoc * 0.1,
          Vec2DD(0.1 * random.nextDouble(), 0.1 * random.nextDouble()),
          Vec2DD(0, 0),
          Math.min(0.85 + random.nextDouble() * 0.25, 1.0),
          -8 + -16 * random.nextDouble() + /*-10.0 * distFromBaseLoc + */ baseDecay,
          -0.75 * random.nextDouble(),
          colorScale
        )
        j += 1
      }

      newParticles += ParticleGroup2D(
        (baseLoc * 0.01) + 0.5,
        Vec2DD(baseLoc.x * (4.5 + random.nextDouble()), baseLoc.y * (4.5 + random.nextDouble())),
        baseLoc * -15,
        Angle(0),
        random.nextDouble() * 10,
        newSubParticles.toArray
      )
      i += 1
    }
    newParticles.toArray
  }
}
