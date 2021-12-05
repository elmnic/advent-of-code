import days.Day2

class Day2Spec extends BaseTest {

  behavior of "Day2"

  ".solve()" should "return the sample output given the sample input" in {
    val testInput = TestInputHelper.getTestInput("2") match {
      case Left(msg) => fail(msg)
      case Right(input) => input
    }
    Day2.solve(testInput) shouldBe(150, 900)
  }
}
