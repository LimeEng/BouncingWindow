package bouncingwindow

trait Gravity extends Ball {

  val gravity = 120 * 4

  override def update(delta: Double): Unit = {
    super.update(delta)
    this.speedY += gravity * delta
  }
}
