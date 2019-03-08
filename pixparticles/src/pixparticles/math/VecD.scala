package pixparticles.math

/**
  * Created by tim on 11/05/17.
  */
trait VecD[V] {

  def +(d: Double): V

  def +(r: V): V

  def *(r: Double): V
}
