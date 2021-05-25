package bouncingwindow

import scalafx.geometry.Rectangle2D.sfxRectangle2D2jfx
import scalafx.scene.paint.Color
import scalafx.stage.Screen

trait DistanceColor extends BallConnection {

  val lengthToScreenRatio = 9

  override def getColor(): Color = {
    val normalizedLength = normalizeLength
    this.width = normalizedLength * 20
    Color.hsb(color.hue, color.saturation, color.brightness, normalizedLength)
  }

  override def isVisible = this.length < maxLength

  override def maxLength(): Double = {
    val screenBounds = Screen.primary.bounds
    math.hypot(screenBounds.getWidth, screenBounds.getHeight) / lengthToScreenRatio
  }

  private def normalizeLength(): Double = {
    val alphaRange = 1
    var newValue = 1 - (((this.length) * alphaRange) / this.maxLength())
    if (newValue < 0) {
      newValue = 0
    } else if (newValue > 1) {
      newValue = 1
    }
    newValue
  }

}