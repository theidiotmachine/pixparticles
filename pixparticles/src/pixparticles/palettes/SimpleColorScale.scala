package pixparticles.palettes

/**
  * Created by tim on 27/04/17.
  */
class SimpleColorScale(val colors: Array[Color]) extends ColorScale{
  override val numberOfColors: Int = colors.length

  override def color(d: Double): Color =
    colors(Math.min(Math.max((d * numberOfColors).round.asInstanceOf[Int], 0), numberOfColors-1))

  override def color(i: Int): Color = colors(i)

  /**
    * The color one shade darker than this color
 *
    * @param d the numerical color value, 0 - 1
    * @return
    */
  override def darkerColor(d: Double): Option[Color] = {
    val idx = (d * numberOfColors).round.asInstanceOf[Int] - 1
    if(idx < 0 || idx >= numberOfColors )
      None
    else
      Option(colors(idx))
  }
}
