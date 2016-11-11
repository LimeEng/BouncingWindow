package bouncingwindow

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.canvas.GraphicsContext.sfxGraphicsContext2jfx
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.scene.shape.StrokeLineCap
import scalafx.scene.shape.StrokeLineCap.sfxEnum2jfx
import scalafx.stage.Stage
import scalafx.stage.Screen

class BallConnection(
  var startBall: Ball,
  var endBall: Ball,
  protected var width: Double = 5,
  protected var color: Color = Color.White,
  val stage: Stage)
    extends Moveable
    with Renderable {

  //  private[this] var isVisible: Boolean = getColor.opacity > 0.1
  //  def visible: Boolean = isVisible

  def maxLength(): Double = {
    val screenBounds = Screen.primary.bounds
    math.hypot(screenBounds.getWidth, screenBounds.getHeight)
  }

  def isVisible = true

  def getColor: Color = this.color

  protected def length = math.hypot(startBall.currentX - endBall.currentX, startBall.currentY - endBall.currentY)

  def update(delta: Double): Unit = {
    // Nothing to update
    // TODO: Revise Renderable and Moveable
  }

  def render(g: GraphicsContext): Unit = {
    val originalColor = g.getFill
    val originalWidth = g.getLineWidth

    val color = this.getColor
    g.setFill(color)
    g.setStroke(color)
    g.setLineWidth(width)
    g.setLineCap(StrokeLineCap.Round)
    g.strokeLine(startBall.currentX, startBall.currentY, endBall.currentX, endBall.currentY)

    g.setFill(originalColor)
    g.setLineWidth(originalWidth)
  }

}
