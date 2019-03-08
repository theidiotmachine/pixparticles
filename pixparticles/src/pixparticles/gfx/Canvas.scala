package pixparticles.gfx

import java.awt.image.BufferedImage

import pixparticles.math.Vec2DI

/**
  * Created by tim on 04/05/17.
  */
case class Canvas(bi: BufferedImage, frameSize: Vec2DI, zoom: Vec2DI, numAnimFrames: Int, numCanvasFrames: Int) {
  def frameOffset(frame: Int): Int = frameSize.x * frame
}
