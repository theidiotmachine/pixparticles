package pixparticles.math

import utest._

object Vec2DDTests extends TestSuite {
  val tests = Tests{
    'test1 - {
      var i = 100
      val random = new StatefulRandom(45678)
      val r = 2
      while(i > 0) {

        val v = Vec2DD.randCirclePoint(r, random)

        val d = v.x * v.x + v.y * v.y
        val i1 = r * r
        assert(d <= i1)

        i -= 1
      }
    }
  }
}
