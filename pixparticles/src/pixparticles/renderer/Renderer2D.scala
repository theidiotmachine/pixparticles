package pixparticles.renderer

import pixparticles.gfx.Canvas
import pixparticles.math.StatefulRandom

/**
  * Created by tim on 27/04/17.
  */
trait Renderer2D {
  def render(canvas: Canvas, numFrames: Int, frameTime: Double, random: StatefulRandom): Unit

}
