package pixparticles.renderer.particle

/**
  * Created by tim on 02/05/17.
  */
trait CoolingParticle {
  val heat: Double
  val heatDecay: Double
  val heatDecayDecay: Double

  protected [this] def evolveHeat(frameTime: Double): (Double, Double) = {

   val newHeatDecay = heatDecay + heatDecayDecay * frameTime
   val newHeat = heat + newHeatDecay * frameTime
   (newHeat, newHeatDecay)
 }
}
