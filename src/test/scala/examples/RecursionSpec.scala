package examples
import org.scalatest.FunSpec
import Recursion._

class RecursionSpec extends FunSpec {
  describe("factorial") {
    it("should give a correct result for positive integers") {
      assert(factorial(0) == 1)
      assert(factorial(1) == 1)
      assert(factorial(2) == 2)
      assert(factorial(3) == 6)
      assert(factorial(10) == 3628800)
    }
  }
}
