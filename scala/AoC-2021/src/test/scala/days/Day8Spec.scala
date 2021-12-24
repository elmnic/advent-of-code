package days

class Day8Spec extends BaseTest {

  "Day8" should "return the sample output given the sample input" in {
    val testInput = getTestInput("8") match {
      case Left(msg)    => fail(msg)
      case Right(input) => input
    }
    val (part1, part2) = new Day8().solve(testInput)

    withClue("Part1: ") {
      part1 shouldBe 26
    }
    withClue("Part2: ") {
      part2 shouldBe 61229
    }
  }
}
