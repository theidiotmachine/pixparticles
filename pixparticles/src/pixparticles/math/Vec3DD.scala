package pixparticles.math

/**
  * Created by tim on 11/05/17.
  */
case class Vec3DD(x: Double, y: Double, z: Double) extends VecD[Vec3DD]{
  type V = Vec3DD
  def +(d: Double) = Vec3DD(x+d, y+d, z+d)

  def +(r: Vec3DD) = Vec3DD(x+r.x, y+r.y, z+r.z)

  def *(r: Double) = Vec3DD(x*r, y*r, z*r)
}
