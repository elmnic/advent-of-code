package days

class Day9Spec extends BaseTest {

  "Day9" should "return the sample output given the sample input" in {
    val testInput = getTestInput("9") match {
      case Left(msg)    => fail(msg)
      case Right(input) => input
    }
    val (part1, part2) = new Day9().solve(testInput)

    withClue("Part1: ") {
      part1 shouldBe 15
    }
    withClue("Part2: ") {
      part2 shouldBe 1134
    }
  }
}
