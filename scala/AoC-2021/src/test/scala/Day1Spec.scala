import days.Day1

class Day1Spec extends BaseTest {

  behavior of "Day1"

  ".solve()" should "return the sample output given the sample input" in {
    val testInput = TestInputHelper.getTestInput("1") match {
      case Left(msg) => fail(msg)
      case Right(input) => input
    }
    Day1.solve(testInput) shouldBe(7, 5)
  }
}
