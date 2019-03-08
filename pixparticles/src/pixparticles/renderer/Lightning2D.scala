package pixparticles.renderer

import pixparticles.gfx.{Canvas, Draw2D}
import pixparticles.math.{StatefulRandom, Vec2DD}
import pixparticles.palettes.{Color, ColorScale}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by tim on 05/05/17.
  */

case class LightningBolt2D(p1: Vec2DD, p2: Vec2DD, p1Vel: Vec2DD, p2Vel: Vec2DD, points: Array[Vec2DD], color: Color)

class Lightning2D(val numBolts: Int, colors: Array[Color]) extends Renderer2D {

  private [this] def generateIntermediatePoints(p1: Vec2DD, p2: Vec2DD, random: StatefulRandom): Array[Vec2DD] = {
    val numIntermediates = 1 + random.nextInt(4)
    var j = 0
    var intermediates = Array.ofDim[Double](numIntermediates)
    while (j < numIntermediates) {
      val r = random.nextDouble()
      intermediates(j) = r
      j += 1
    }

    intermediates = intermediates.sorted

    val points = intermediates.map(r => {
      val intLoc = p1 * (1 - r) + p2 * r
      Vec2DD(intLoc.x + math.abs(p2.x - 0.5) * (random.nextDouble() - 0.5), intLoc.y)
    })
    points
  }

  private [this] def initBolts(random: StatefulRandom): Array[LightningBolt2D] = {
    var i = 0
    val out = ArrayBuffer[LightningBolt2D]()
    while(i < numBolts){
      //val startLoc = Vec2DD((random.nextDouble() - 0.5) * 0.2 + 0.5, 0.2)//Vec2DD.randCirclePoint(1, random) * 0.15 + Vec2DD(0, 0.2)
      //val endLoc = Vec2DD((random.nextDouble() - 0.5) * 0.2 + 0.5, 0.8)

      val p1 = Vec2DD(0.5, 0.0)//Vec2DD.randCirclePoint(1, random) * 0.15 + Vec2DD(0, 0.2)
      val p2 = Vec2DD(0.5, 0.0)
      val p1Vel = Vec2DD(0, 0)//(random.nextDouble() - 0.5) * 2, 2)
      val p2Vel = Vec2DD((random.nextDouble() - 0.5) * 5, 10.1)

      val points: Array[Vec2DD] = generateIntermediatePoints(p1, p2, random)

      val color = colors(i % colors.length)

      out += LightningBolt2D(p1, p2, p1Vel, p2Vel, points, color)

      i += 1
    }

    out.toArray
  }

  private [this] def renderBolts(bolts: Array[LightningBolt2D], canvas: Canvas, frame: Int) = {
    bolts.foreach(bolt=>{
      val fullPoints = (bolt.p1 +: bolt.points) :+ bolt.p2
      val oTransformedFullPoints = fullPoints.map(fp=>{
        Draw2D.transform(fp, canvas, frame)
      })
      var i = 0
      while(i < oTransformedFullPoints.length - 1){
        (oTransformedFullPoints(i), oTransformedFullPoints(i+1)) match {
          case (Some(p1), Some(p2)) =>
            Draw2D.drawLine(p1, p2, bolt.color, canvas, frame)
          case _ =>
        }
        i += 1
      }
    })
  }

  private [this] def evolveBolts(bolts: Array[LightningBolt2D], frameTime: Double, random: StatefulRandom): Array[LightningBolt2D] = {
    bolts.map(bolt=>{
      //val newStartLoc = Vec2DD(bolt.p1.x + (random.nextDouble() - 0.5) * 0.05, bolt.p1.y)//Vec2DD.randCirclePoint(1, random) * 0.01 + bolt.p1
      //val newEndLoc = Vec2DD(bolt.p2.x + (random.nextDouble() - 0.5) * 0.05, bolt.p2.y)
      val newp1 = bolt.p1 + bolt.p1Vel * frameTime
      val newp2 = bolt.p2 + bolt.p2Vel * frameTime
      val points: Array[Vec2DD] = generateIntermediatePoints(newp1, newp2, random)
      LightningBolt2D(newp1, newp2, bolt.p1Vel, bolt.p2Vel, points, bolt.color)
    })
  }

  override def render(canvas: Canvas, numFrames: Int, frameTime: Double, random: StatefulRandom): Unit = {

    var bolts = initBolts(random)

    var frame = 0
    while(frame < numFrames){
      renderBolts(bolts, canvas, frame)
      bolts = evolveBolts(bolts, frameTime, random)

      frame += 1
    }
  }
}
