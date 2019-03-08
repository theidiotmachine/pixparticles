package pixparticles.palettes

/**
  * 24 is red, where 0 is cold and 6 is hot
  */
object LPC extends Palette {
  private [this] val colorScales: Array[SimpleColorScale] = Array(
    //0 - brown 1
    new SimpleColorScale(Array[Color]()),
    //1 - brown 2
    new SimpleColorScale(Array[Color]()),
    //2 - brown 3
    new SimpleColorScale(Array[Color]()),
    //3 - brown 4
    new SimpleColorScale(Array[Color]()),
    //4 - brown 5
    new SimpleColorScale(Array[Color]()),
    //5 - brown 6
    new SimpleColorScale(Array[Color]()),
    //6 - brown 7
    new SimpleColorScale(Array[Color]()),
    //7 - blue 1
    new SimpleColorScale(Array[Color]()),
    //8 - green 1
    new SimpleColorScale(Array[Color]()),
    //9 - green 2
    new SimpleColorScale(Array[Color]()),
    //10 - green 3
    new SimpleColorScale(Array[Color]()),
    //11 - grey 1
    new SimpleColorScale(Array[Color]()),
    //12 - grey 2
    new SimpleColorScale(Array[Color]()),
    //13 - grey 3
    new SimpleColorScale(Array[Color]()),
    //14 - blue 1
    new SimpleColorScale(Array[Color]()),
    //15 - blue 2
    new SimpleColorScale(Array[Color]()),
    //16 - blue 3
    new SimpleColorScale(Array[Color](
      Color(0x474ea5),
      Color(0x5a72dd),
      Color(0x61a0ef),
      Color(0x83c6ff)
    )),
    //17 - brown 8
    new SimpleColorScale(Array[Color]()),
    //18 - brown 9
    new SimpleColorScale(Array[Color]()),
    //19 - brown 10
    new SimpleColorScale(Array[Color]()),
    //20 - brown 11
    new SimpleColorScale(Array[Color]()),
    //21 - brown 12
    new SimpleColorScale(Array[Color]()),
    //22 - brown 13
    new SimpleColorScale(Array[Color]()),
    //23 - aqua 1
    new SimpleColorScale(Array[Color]()),
    //24 - red 1
    new SimpleColorScale(Array[Color](
      Color(0x27232a), Color(0x523745), Color(0xae424a), Color(0xe43c3c), Color(0xff5442), Color(0xff7b3a), Color(0xffa749)
    )),
    //25 - blue 4
    new SimpleColorScale(Array[Color]()),
    //26 - purple 1
    new SimpleColorScale(Array[Color]())
  )

  override val numColorScales: Int = colorScales.length
  override def colorScale(i: Int): ColorScale = colorScales(i)
}
