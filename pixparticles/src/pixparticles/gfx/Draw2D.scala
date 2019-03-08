package pixparticles.gfx

import java.awt.image.BufferedImage

import pixparticles.math.{Vec2DD, Vec2DI}
import pixparticles.palettes.Color

/**
  * Created by tim on 04/05/17.
  */
object Draw2D {

  def drawPixelNoClip(loc: Vec2DI, bi: BufferedImage, color: Color): Unit = {
    bi.setRGB(loc.x, loc.y, color.toInt)
  }

  def drawPixel(loc: Vec2DI, color: Color, canvas: Canvas, frame: Int): Unit = {
    val x = loc.x - canvas.frameOffset(frame)
    if(x >= 0 && x < canvas.frameSize.x) {
      if(loc.y >= 0 && loc.y < canvas.frameSize.y) {
        drawPixelNoClip(loc, canvas.bi, color)
      }
    }
  }

  def transform(loc: Vec2DD, canvas: Canvas, frame: Int): Option[Vec2DI] = {
    val xD = (loc.x * canvas.zoom.x).round.asInstanceOf[Int]
    if (xD >= 0 && xD < canvas.frameSize.x) {
      val yD = (loc.y * canvas.zoom.y).round.asInstanceOf[Int]
      if (yD >= 0 && yD < canvas.frameSize.y) {
        Option(Vec2DI(xD + canvas.frameOffset(frame), yD))
      } else
        None
    } else
      None
  }

  def transform(loc: Vec2DD, origin: Vec2DD, canvas: Canvas, frame: Int): Option[Vec2DI] = {
    val finalLoc = loc + origin
    transform(finalLoc, canvas, frame)
  }

  /**
    * bresenham
    * @param from
    * @param to
    * @param c
    * @param canvas
    * @param frame
    */
  def drawLine(from: Vec2DI, to: Vec2DI, c: Color, canvas: Canvas, frame: Int): Unit = {
    val w = to.x - from.x
    val h = to.y - from.y
    var dx1 = 0
    var dy1 = 0
    var dx2 = 0
    var dy2 = 0
    if(w<0) dx1 = -1
    else if(w>0) dx1 = 1

    if(h<0) dy1 = -1
    else if(h > 0) dy1 = 1

    if(w<0) dx2 = -1
    else if(w>0) dx2 = 1

    var longest = Math.abs(w)
    var shortest = Math.abs(h)
    if(!(longest>shortest)) {
      //swap
      longest = Math.abs(h)
      shortest = Math.abs(w)
      if(h<0) dy2 = -1
      else if(h > 0) dy2 = 1
      dx2 = 0
    }
    var numerator = longest >> 1
    var i = 0
    var x = from.x
    var y = from.y
    while(i<=longest){

      drawPixel(Vec2DI(x, y), c, canvas, frame)
      numerator += shortest
      if(!(numerator < longest)) {
        numerator -= longest
        x += dx1
        y += dy1
      } else {
        x += dx2
        y += dy2
      }
      i += 1
    }
  }
}
