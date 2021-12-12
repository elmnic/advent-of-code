package days

class Day4Spec extends BaseTest {

  "Day4" should "return the sample output given the sample input" in {
    val testInput = getTestInput("4") match {
      case Left(msg)    => fail(msg)
      case Right(input) => input
    }
    val (part1, part2) = new Day4().solve(testInput)

    withClue("Part1: ") {
      part1 shouldBe 4512
    }
    withClue("Part2: ") {
      part2 shouldBe 1924
    }
  }
}
