package days


import org.scalatest.DoNotDiscover
import util.TestInputHelper

@DoNotDiscover
class Day5Spec extends BaseTest {

  "Day5" should "return the sample output given the sample input" in {

    val testInput = TestInputHelper.getTestInput("5") match {
      case Left(msg)    => fail(msg)
      case Right(input) => input
    }
    val (part1, part2) = new Day5().solve(testInput)

    withClue("Part1: ") {
      part1 shouldBe 5
    }
    withClue("Part2: ") {
      part2 shouldBe 12
    }
  }
}
