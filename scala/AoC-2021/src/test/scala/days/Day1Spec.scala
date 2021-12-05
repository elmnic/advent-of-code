package days

import org.scalatest.DoNotDiscover
import util.TestInputHelper

@DoNotDiscover
class Day1Spec extends BaseTest {

  behavior of "Day1"

  ".solve()" should "return the sample output given the sample input" in {
    val testInput = TestInputHelper.getTestInput("1") match {
      case Left(msg) => fail(msg)
      case Right(input) => input
    }
    val (part1, part2) = new Day1().solve(testInput)

    withClue("Part1: ") {
      part1 shouldBe 7
    }
    withClue("Part2: ") {
      part2 shouldBe 5
    }
  }
}