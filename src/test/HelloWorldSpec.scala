import org.scalatest._
// import org.scalatest.FlatSpec
// class HelloWorldSpec extends

class HelloWorldSpec extends FlatSpec with Matchers {
  it should {
    "do y" in {
      assert("a" != "b")
    }
  }
}
