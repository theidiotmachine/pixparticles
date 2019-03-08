package pixparticles.gfx

import pixparticles.math.Vec2DI
import pixparticles.palettes.Color

/**
  * Created by tim on 11/05/17.
  */

class ZBufferElem(var colorInt: Int, var filled: Boolean = false, var depth: Int = 0)
case class ZBuffer(frameSize: Vec2DI, numAnimFrames: Int, numCanvasFrames: Int) {
  //def drawPixelNoClip(rl: Vec3DI, color: Color): Unit = {

  //}

}
