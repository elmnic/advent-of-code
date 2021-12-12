package days

class Day7Spec extends BaseTest {

  "Day7" should "return the sample output given the sample input" in {
    val testInput = getTestInput("7") match {
      case Left(msg)    => fail(msg)
      case Right(input) => input
    }
    val (part1, part2) = new Day7().solve(testInput)

    withClue("Part1: ") {
      part1 shouldBe 37
    }
    withClue("Part2: ") {
      part2 shouldBe 168
    }
  }
}
