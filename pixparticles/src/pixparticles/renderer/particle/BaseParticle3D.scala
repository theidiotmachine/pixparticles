package pixparticles.renderer.particle

import pixparticles.gfx.{Camera, Canvas, ZBuffer}
import pixparticles.math.{StatefulRandom, Vec3DD}

/**
  * Created by tim on 11/05/17.
  */
trait BaseParticle3D[P] extends BaseParticle[P] {
  type V = Vec3DD
  def render(origin: Vec3DD, camera: Camera, canvas: ZBuffer, frame: Int): Unit
}
