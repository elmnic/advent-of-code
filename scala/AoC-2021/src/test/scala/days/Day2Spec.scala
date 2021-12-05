package days

import org.scalatest.DoNotDiscover
import util.TestInputHelper

@DoNotDiscover
class Day2Spec extends BaseTest {

  behavior of "Day2"

  ".solve()" should "return the sample output given the sample input" in {
    val testInput = TestInputHelper.getTestInput("2") match {
      case Left(msg) => fail(msg)
      case Right(input) => input
    }
    val (part1, part2) = Day2.solve(testInput)

    withClue("Part1: ") {
      part1 shouldBe 150
    }
    withClue("Part2: ") {
      part2 shouldBe 900
    }
  }
}
