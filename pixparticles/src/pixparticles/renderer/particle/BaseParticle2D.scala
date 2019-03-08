package pixparticles.renderer.particle

import pixparticles.gfx.Canvas
import pixparticles.math.{StatefulRandom, Vec2DD}

/**
  * Created by tim on 08/05/17.
  */
trait BaseParticle2D[P] extends BaseParticle[P]{
  type V = Vec2DD
  def render(origin: Vec2DD, canvas: Canvas, frame: Int): Unit
}
