package pixparticles.palettes

/**
  * Created by tim on 27/04/17.
  */
trait ColorScale {
  val numberOfColors: Int
  def color(d: Double): Color
  def color(i: Int): Color
  def darkerColor(d: Double): Option[Color]
}
