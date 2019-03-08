package pixparticles.renderer.particle

import pixparticles.gfx.{Camera, Canvas, ZBuffer}
import pixparticles.math.{StatefulRandom, Vec2DD, Vec3DD}
import pixparticles.palettes.{Color, ColorScale}

/**
  * Created by tim on 11/05/17.
  */
case class SimpleParticle3D(loc: Vec3DD, vel: Vec3DD, acc: Vec3DD,
                            heat: Double, heatDecay: Double, heatDecayDecay: Double,
                            colorScale: ColorScale) extends Particle3D with DynamicParticleNoRotate[Vec3DD] with CoolingParticle {
  def color: Color = {
    colorScale.color(heat)
  }

  override def evolve(frameTime: Double, random: StatefulRandom): Array[Particle3D] = {
    val (newHeat, newHeatDecay) = evolveHeat(frameTime)
    if(newHeat < 0) {
      Array()
    } else {
      val (newPos, newVel) = evolvePhysicsNoRotate(frameTime)
      Array(SimpleParticle3D(newPos, newVel, acc, newHeat, newHeatDecay, heatDecayDecay, colorScale))
    }
  }

  override def render(origin: Vec3DD, camera: Camera, canvas: ZBuffer, frame: Int): Unit = {
    drawPixel(loc, origin, camera, color, canvas, frame)
  }
}
