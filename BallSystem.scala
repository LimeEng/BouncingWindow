package bouncingwindow

import scala.collection.mutable.ArrayBuffer

import scalafx.scene.canvas.GraphicsContext

class BallSystem extends Moveable with Renderable {

  var balls: ArrayBuffer[Ball] = ArrayBuffer.empty
  var connections: ArrayBuffer[BallConnection] = ArrayBuffer.empty

  private[this] val MAX_BALLS = 1000

  def distance(b1: Ball, b2: Ball): Double = {
    math.hypot(b1.currentX - b2.currentX, b1.currentY - b2.currentY)
  }

  def reset() {
    balls.clear()
    connections.clear()
  }

  def add(l: BallConnection) {
    connections :+= l
  }

  def remove(l: BallConnection) {
    connections.-=(l)
  }

  def add(b: Ball) {
    if (balls.size >= MAX_BALLS) {
      return
    }
    balls :+= b
    if (balls.length > 1) {
      for (ball <- balls) {
        if (b.getRadius > 60 && ball.getRadius > 60) {
          this.add(new BallConnection(startBall = b, endBall = ball, stage = b.stage) with DistanceColor)
        }
      }
    }
  }

  def remove(b: Ball) {
    balls.-=(b)
  }

  def update(delta: Double): Unit = {
    balls.foreach(_.update(delta))

    // Removes all the lines that are not visible
    //    val connectionsToRemove = connections.filter { x => !x.visible }
    //    connections.--=(connectionsToRemove)

    // TODO: Create lines that should be visible

  }

  def render(g: GraphicsContext): Unit = {
    balls.foreach(_.render(g))
    // Only renders the visible lines => Greatly increases performance! 
    connections.filter { x => x.isVisible }.foreach { x => x.render(g) }
  }

}
