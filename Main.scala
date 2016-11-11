package bouncingwindow

import scalafx.Includes.eventClosureWrapperWithParam
import scalafx.Includes.jfxKeyEvent2sfx
import scalafx.Includes.jfxMouseEvent2sfx
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.geometry.Rectangle2D.sfxRectangle2D2jfx
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.canvas.GraphicsContext.sfxGraphicsContext2jfx
import scalafx.scene.input.KeyCode
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyEvent.sfxKeyEvent2jfx
import scalafx.scene.input.MouseEvent
import scalafx.scene.input.MouseEvent.sfxMouseEvent2jfx
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.stage.Screen

// This import will fix most things:
// import scalafx.Includes._

object Main extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title.value = "Hello World!"
    //    this.fullScreen_=(true)
    this.maximized_=(true)
    this.resizable_=(true)

    val screenBounds = Screen.primary.bounds

    def frameWidth = screenBounds.getWidth
    def frameHeight = screenBounds.getHeight

    val ballSystem = new BallSystem()

    for (i <- 1 to 30) {
      for (k <- 1 to 30) {
        // ballSystem.add((new Ball(currentX = 30 * i, currentY = 30 * k, speedY = 90, speedX = 90, radius = 10, stage = this) with Gravity with ColorShifter))
      }
    }

    def createRandomBall(
      currentX: Double = math.random * frameWidth,
      currentY: Double = math.random * frameHeight,
      speedX: Double = math.random * 240 * 1 - 120 * 1,
      speedY: Double = math.random * 240 * 1 - 120 * 1,
      radius: Double = math.random * 70 + 10): Ball = {
      new Ball(currentX, currentY, speedX, speedY, radius, Color.Aqua, stage) with ColorShifter
    }

    scene = new Scene {
      fill = Color.Black
      val canvas = new Canvas(frameWidth, frameHeight)
      val g = canvas.graphicsContext2D
      g.setStroke(Color.White)
      g.setLineWidth(10)
      content = canvas

      this.onKeyPressed = (e: KeyEvent) => {
        if (e.code == KeyCode.Space) {
          for (k <- 1 to 10) {
            ballSystem.add(createRandomBall())
          }
        } else if (e.code == KeyCode.Q) {
          println("Number of balls: " + ballSystem.balls.size)
          println("Number of lines: " + ballSystem.connections.size)
          System.exit(0)
        } else if (e.code == KeyCode.Enter) {
          for (i <- 1 to 30) {
            ballSystem.add(createRandomBall(currentX = frameWidth / 30 * i, currentY = 0))
            ballSystem.add(createRandomBall(currentX = frameWidth / 30 * i, currentY = frameHeight))
            ballSystem.add(createRandomBall(currentY = frameHeight / 30 * i, currentX = 0))
            ballSystem.add(createRandomBall(currentY = frameHeight / 30 * i, currentX = frameWidth))
          }

        } else if (e.code == KeyCode.K) {
          for (i <- 1 to 10) {
            ballSystem.add(createRandomBall(currentX = frameWidth / 2, currentY = frameHeight / 2))
          }
        } else if (e.code == KeyCode.BackSpace) {
          if (e.isShiftDown()) {
            ballSystem.reset()
          }
        }

      }

      this.onMouseDragged = (e: MouseEvent) => {
        if (e.isShiftDown()) {
          // TODO: Destroy ball
        } else {
          ballSystem.add(createRandomBall(currentX = e.getX, currentY = e.getY))
        }
      }

      this.onMouseClicked = (e: MouseEvent) => {
        if (e.isShiftDown()) {
          // TODO: Destroy ball
        } else {
          ballSystem.add(createRandomBall(currentX = e.getX, currentY = e.getY))
        }
      }

      var lastTime = 0L
      val timer = AnimationTimer(t => {
        if (lastTime > 0) {
          val delta = (t - lastTime) / 1e9
          g.clearRect(0, 0, frameWidth, frameHeight)
          ballSystem.update(delta)
          ballSystem.render(g)
        }
        lastTime = t
      })
      timer.start()
    }
  }
}
