package bouncingwindow

import scalafx.scene.canvas.GraphicsContext

trait Renderable {
  def render(g: GraphicsContext): Unit
}
