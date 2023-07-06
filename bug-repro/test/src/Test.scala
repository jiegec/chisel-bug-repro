package bugrepro

import chisel3._
import chiseltest._
import org.scalatest.freespec.AnyFreeSpec

class TestTest extends AnyFreeSpec with ChiselScalatestTester {
  s"Test should work" in {
    test(new Test()) { dut =>
      dut.clock.step(16)
    }
  }
}
