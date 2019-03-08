package pixparticles.gfx

import pixparticles.math.{Vec2DI, Vec3DD}

/**
  * Created by tim on 11/05/17.
  */
trait Camera {
  def transform(loc: Vec3DD, origin: Vec3DD, canvas: ZBuffer, frame: Int): Option[Vec2DI]

}
