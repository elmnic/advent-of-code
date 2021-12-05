package days

import org.scalatest.DoNotDiscover
import util.TestInputHelper

@DoNotDiscover
class Day3Spec extends BaseTest {

  behavior of "Day3"

  ".solve()" should "return the sample output given the sample input" in {
    val testInput = TestInputHelper.getTestInput("3") match {
      case Left(msg) => fail(msg)
      case Right(input) => input
    }
    val (part1, part2) = Day3.solve(testInput)
    withClue("Part1: ") {
      part1 shouldBe 198
    }
    withClue("Part2: ") {
      part2 shouldBe 230
    }
  }
}
