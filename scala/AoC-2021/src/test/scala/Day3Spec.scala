import days.Day3

class Day3Spec extends BaseTest {

  behavior of "Day3"

  ".solve()" should "return the sample output given the sample input" in {
    val testInput = TestInputHelper.getTestInput("3") match {
      case Left(msg) => fail(msg)
      case Right(input) => input
    }
    Day3.solve(testInput) shouldBe(198, 230)
  }
}
