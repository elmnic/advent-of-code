package days

import org.scalatest.DoNotDiscover
import util.TestInputHelper

@DoNotDiscover
class Day6Spec extends BaseTest {

  "Day6" should "return the sample output given the sample input" in {
    val testInput = TestInputHelper.getTestInput("6") match {
      case Left(msg)    => fail(msg)
      case Right(input) => input
    }
    val (part1, part2) = new Day6().solve(testInput)

    withClue("Part1: ") {
      part1 shouldBe 5934
    }
    withClue("Part2: ") {
      part2 shouldBe 26984457539L
    }
  }
}
