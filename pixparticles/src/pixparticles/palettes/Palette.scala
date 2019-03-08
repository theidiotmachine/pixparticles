package pixparticles.palettes

/**
  * Created by tim on 27/04/17.
  */
trait Palette {
  val numColorScales: Int
  def colorScale(i: Int): ColorScale
}
