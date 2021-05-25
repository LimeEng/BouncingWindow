package bouncingwindow

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.canvas.GraphicsContext.sfxGraphicsContext2jfx
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.stage.Stage

class Ball(
  var currentX: Double = 0.0,
  var currentY: Double = 0.0,
  var speedX: Double = 0.0,
  var speedY: Double = 0.0,
  private var radius: Double,
  private var color: Color = Color.Aqua,
  val stage: Stage)
    extends Moveable
    with Renderable {

  def frameWidth = stage.width.value
  def frameHeight = stage.height.value

  def getColor: Color = this.color

  def getRadius: Double = radius

  def update(delta: Double): Unit = {
    currentX += speedX * delta
    currentY += speedY * delta
    if (currentX - radius < 0) {
      speedX = -speedX
      currentX = radius
    } else if (currentX + radius > frameWidth) {
      speedX = -speedX
      currentX = frameWidth - radius
    } else if (currentY - radius < 0) {
      speedY = -speedY
      currentY = radius
    } else if (currentY + radius > frameHeight) {
      speedY = -speedY
      currentY = frameHeight - radius
    }
  }

  def render(g: GraphicsContext): Unit = {
    val originalColor = g.getFill

    g.setFill(getColor)
    g.fillOval(currentX - radius, currentY - radius, radius * 2, radius * 2)

    g.setFill(originalColor)
  }
}
