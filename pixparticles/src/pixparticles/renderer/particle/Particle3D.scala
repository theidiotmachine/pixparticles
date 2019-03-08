package pixparticles.renderer.particle

import pixparticles.gfx.{Camera, Canvas, Draw2D, ZBuffer}
import pixparticles.math.{Vec2DD, Vec3DD}
import pixparticles.palettes.Color

/**
  * Created by tim on 11/05/17.
  */
trait Particle3D extends BaseParticle3D[Particle3D] {
  def loc: Vec3DD

  protected [this] def drawPixel(loc: Vec3DD, origin: Vec3DD, camera: Camera, color: Color, canvas: ZBuffer, frame: Int): Unit = {
    val renderLoc = camera.transform(loc, origin, canvas, frame)
    //renderLoc.foreach(rl=>canvas.drawPixelNoClip(rl, color))
  }
}
