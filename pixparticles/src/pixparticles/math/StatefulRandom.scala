package pixparticles.math

/**
  * A xorshift rng, from [Xorshift RNGs, George Marsaglia]
  *
  * what an awesome paper
  */
class StatefulRandom(var state: Long) {

  def nextLong(): Long = {
    state ^= (state << 13)
    state ^= (state >>> 7)
    state ^= (state << 17)
    state
  }

  def nextDouble(): Double = {
    (nextLong() & 0x1FFFFFFFFFFFFFL) * 1.11022302462515666368e-16
  }

  def nextInt(range: Int): Int = {
    var out = nextLong() & 0x7FFFFFFFFFFFFFFFL
    while(out >= (Long.MaxValue - (Long.MaxValue % range)))
      out = nextLong() & 0x7FFFFFFFFFFFFFFFL

    (out % range).asInstanceOf[Int]
  }
}

