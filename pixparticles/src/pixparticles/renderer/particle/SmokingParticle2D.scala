package pixparticles.renderer.particle

import java.awt.image.BufferedImage

import pixparticles.gfx.Canvas
import pixparticles.math.{Angle, StatefulRandom, Vec2DD}
import pixparticles.palettes.{Color, ColorScale}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by tim on 01/05/17.
  */
case class SmokingParticle2D(ember: Particle2D with CoolingParticle with DynamicParticleNoRotate[Vec2DD], smokeColorScale: ColorScale)
  extends Particle2D {

  override def loc: Vec2DD = ember.loc
  private [this] def makeSmokePuff(random: StatefulRandom) = {
    val newSubParticles = ArrayBuffer[Particle2D]()
    val baseLoc = Vec2DD.randCirclePoint(1, random)

    val numSubParticles = 10 + random.nextInt(10)
    val baseDecay = -3.0 * random.nextDouble()
    var j = 0
    while(j < numSubParticles) {
      val subBaseLoc = Vec2DD.randCirclePoint(1, random)

      newSubParticles += SimpleParticle2D(
        subBaseLoc * 0.02,
        Vec2DD(2.5 * (random.nextDouble() - 0.5), 2.5 * (random.nextDouble()-0.5)),
        Vec2DD(0, 0),
        Math.min(random.nextDouble() * 0.5, 0.2),
        -8 * random.nextDouble() + baseDecay,
        0.0,
        smokeColorScale
      )
      j += 1
    }

    ParticleGroup2D(
      //Vec2DD(0.5 + baseLoc.x * 0.02, baseLoc.y * 0.02 + 0.9),
      loc + baseLoc.x * 0.02,
      ember.vel + Vec2DD(random.nextDouble() - 0.5, -2.0 + -5.0 * random.nextDouble()),
      Vec2DD(0, -10 + -10 * random.nextDouble()),
      Angle(0),
      random.nextDouble() * 10,
      newSubParticles.toArray
    )
  }

  override def evolve(frameTime: Double, random: StatefulRandom): Array[Particle2D] = {
    val newEmber = ember.evolve(frameTime, random)
    if(newEmber.nonEmpty){
      //yuck
      val out = ArrayBuffer[Particle2D](
        SmokingParticle2D(
          newEmber(0).asInstanceOf[Particle2D with CoolingParticle with DynamicParticleNoRotate[Vec2DD]],
          smokeColorScale))
      if(random.nextDouble() > 0.8 && ember.heat > 0.4)
        out += makeSmokePuff(random)
      out.toArray
    } else {
      Array()
    }
  }

  override def render(origin: Vec2DD, canvas: Canvas, frame: Int): Unit = {
    ember.render(origin, canvas, frame)
  }
}
