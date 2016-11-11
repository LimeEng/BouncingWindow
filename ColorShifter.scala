package bouncingwindow

import scala.util.Random

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx

trait ColorShifter extends Ball {

  var alpha: Double = 0.3
  var hue: Double = 1.0
  val saturation: Double = 1.0
  val brightness: Double = 1.0

  val colorOscillator = new Oscillator(0, 360, this.randomNumber(0.05, 0.7))
  val alphaOscillator = new Oscillator(0.2, 0.7, this.randomNumber(0.05, 0.7))

  override def getColor(): Color = {
    // Color.hsb(colorOscillator.step() / hue, saturation, brightness, alphaOscillator.step())
    val temp = Color.hsb(colorOscillator.step(), saturation, brightness, alphaOscillator.step())
    //        this.stage.scene.value.setFill(Color.hsb(temp.hue, temp.saturation, 0.2))
    temp

  }

  private def randomNumber(min: Double, max: Double): Double = {

    (new Random).nextDouble() * (max - min) + min
  }

}
