package pixparticles.renderer.particle

import pixparticles.gfx.Canvas
import pixparticles.math.{StatefulRandom, Vec2DD}

/**
  * Created by tim on 07/05/17.
  */
case class LoopParticle2D(frameOffset: Int, particle2D: Particle2D) extends BaseParticle2D[LoopParticle2D] {

  def evolve(frameTime: Double, random: StatefulRandom): Array[LoopParticle2D] =
    particle2D.evolve(frameTime, random).map(x=>LoopParticle2D(frameOffset, x))

  def render(origin: Vec2DD, canvas: Canvas, frame: Int): Unit = {
    //if frameOffset = 0, we could skip the first copy?
    //if(frameOffset != 0)
      particle2D.render(origin, canvas, frame + frameOffset)
    particle2D.render(origin, canvas, frame + frameOffset + canvas.numAnimFrames)
  }

}
