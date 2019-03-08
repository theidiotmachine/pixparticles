package pixparticles.renderer

import pixparticles.gfx.{Camera, ZBuffer}
import pixparticles.math.StatefulRandom

/**
  * Created by tim on 11/05/17.
  */
trait Renderer3D {
  def render(canvas: ZBuffer, camera: Camera, numFrames: Int, frameTime: Double, random: StatefulRandom): Unit
}
