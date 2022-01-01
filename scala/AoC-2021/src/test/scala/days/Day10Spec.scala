package days

class Day10Spec extends BaseTest {

  "Day10" should "return the sample output given the sample input" in {
    val testInput = getTestInput("10") match {
      case Left(msg)    => fail(msg)
      case Right(input) => input
    }
    val (part1, part2) = new Day10().solve(testInput)

    withClue("Part1: ") {
      part1 shouldBe 26397
    }
    withClue("Part2: ") {
      part2 shouldBe 288957
    }
  }
}
