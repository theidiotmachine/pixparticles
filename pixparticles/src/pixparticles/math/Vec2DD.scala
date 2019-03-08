package pixparticles.math

import scala.util.Random


/**
  * Created by tim on 27/04/17.
  */
case class Vec2DD(x: Double, y: Double) extends VecD[Vec2DD]{
  type V = Vec2DD
  def len: Double = Math.sqrt(x*x + y*y)

  def +(d: Double) = Vec2DD(x+d, y+d)

  def +(r: Vec2DD) = Vec2DD(x+r.x, y+r.y)

  def *(r: Double) = Vec2DD(x*r, y*r)
}

object Vec2DD {
  /**
    * generate a point in a circle of radius r, centred at the origin. from SO 5837572
    * @param r
    * @return
    */
  def randCirclePoint(r: Double, random: StatefulRandom): Vec2DD = {
    var a = random.nextDouble()
    var b = random.nextDouble()
    if(b < a){
      val x = a
      a = b
      b = x
    }
    val y = 2.0 * Math.PI * a / b
    Vec2DD(b * r * Math.cos(y), b * r * Math.sin(y))
  }
}
