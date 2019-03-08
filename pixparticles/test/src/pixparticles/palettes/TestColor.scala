package pixparticles.palettes

import utest._

object TestColor extends TestSuite {
  val tests = Tests{
    'testReverse - {
      val i = 0xff2b1c1d
      val color = Color(i)
      val colorInt = color.toInt
      assert(colorInt == i)
    }
  }
}
