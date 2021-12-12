package days

class Day3Spec extends BaseTest {

  "Day3" should "return the sample output given the sample input" in {
    val testInput = getTestInput("3") match {
      case Left(msg)    => fail(msg)
      case Right(input) => input
    }
    val (part1, part2) = new Day3().solve(testInput)

    withClue("Part1: ") {
      part1 shouldBe 198
    }
    withClue("Part2: ") {
      part2 shouldBe 230
    }
  }
}
