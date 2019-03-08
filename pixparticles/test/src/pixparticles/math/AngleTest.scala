package pixparticles.math

import utest._

object AngleTests extends TestSuite{
  val tests = Tests{
    'test1 - {
      val a1 = Angle(1.0f)
      val a2 = Angle(1.1f)

      assert(a1 ⟲ a2)
      assert(a2 ⟳ a1)
      assert(!(a1 ⟲ a1))
    }
  }
}
