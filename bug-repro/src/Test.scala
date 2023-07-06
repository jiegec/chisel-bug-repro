package bugrepro

import chisel3._

class Test(arg: String = null)
    extends Module {
  val io = IO(new Bundle {})
  println(this, arg)
}

object Test {}
