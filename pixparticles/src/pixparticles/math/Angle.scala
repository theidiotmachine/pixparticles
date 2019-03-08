package pixparticles.math

class Angle(val a: Double) extends AnyVal {

  def /(rhs: Int): Angle = new Angle(a / rhs)
  def /(rhs: Angle): Angle = new Angle(a / rhs.a)

  def +(rhs: Angle) = Angle(a + rhs.a)

  def *(rhs: Int): Angle = new Angle(a * rhs)
  def *(rhs: Float): Angle = new Angle(a * rhs)

  def normalize: Angle = {
    val thetaD = a.toDouble
    new Angle( (thetaD - Angle.TwoPi * Math.floor((thetaD + Math.PI) / Angle.TwoPi)).toFloat )
  }

  def sin: Double = Math.sin(a)

  def cos: Double = Math.cos(a)

  def -(rhs: Angle): Angle = {
    val diff1 = a - rhs.a
    if(diff1 > Angle.Pi)
      new Angle(diff1 - Angle.TwoPi)
    else if(diff1 < Angle.MinusPi)
      new Angle(diff1 + Angle.TwoPi)
    else
      new Angle(diff1)
  }

  def ⟳(rhs: Angle): Boolean = {
    val d = this.-(rhs)
    if(d.a == Angle.Pi)
      throw new RuntimeException("Opposite angle")
    d.a > 0.0f
  }

  def ⟲(rhs: Angle): Boolean = {
    val d = this.-(rhs)
    if(d.a == Angle.Pi)
      throw new RuntimeException("Opposite angle")
    d.a < 0.0f
  }
}

object Angle {
  val Pi: Double = Math.PI
  val TwoPi: Double = Pi * 2.0f
  val MinusPi: Double = -Pi
  def apply(a: Double): Angle = new Angle(a).normalize
}
