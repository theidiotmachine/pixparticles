package pixparticles.palettes

/**
  * Created by tim on 27/04/17.
  */
class Color(val r: Int, val g: Int, val b: Int) {
  def toInt: Int = (255 << 24) | (r << 16) | (g << 8) | b
}

object Color {
  def apply(r: Int, g: Int, b: Int): Color = new Color(r, g, b)
  def apply(raw: Int): Color = new Color((raw >> 16) & 0xff, (raw>>8) & 0xff, raw & 0xff)
}
