
package bouncingwindow

class Oscillator(
    min: Double, max: Double, speed: Double,
    private[this] var angle: Double = 0) {

  val range = max - min
  val amplitude = range
  val average = (max + min) / 2

  def step(): Double = {
    angle += 0.3
    if (angle >= 360) {
      angle = 0
    }
    (amplitude / 2) * Math.sin(Math.toRadians(angle) /* *speed */ ) + average
  }

  // TODO: Temp
  def printAngle = println(angle)
}
