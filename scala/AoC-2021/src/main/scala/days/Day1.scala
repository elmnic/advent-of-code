package days

object Day1 {
  def solve(input: Seq[String]): Unit = {
    val testInput = Seq(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

    val resultPart1 = input.map(_.toInt)
      .sliding(2)
      .collect { case Seq(a1, a2) =>
        if (a1 < a2) 1 else 0
      }
      .sum

    val resultPart2 = input.map(_.toInt)
      .sliding(3)
      .map(_.sum)
      .sliding(2)
      .collect { case Seq(a1, a2) =>
        if (a1 < a2) 1 else 0
      }
      .sum

    println(s"Day 1 - part 1: $resultPart1, part 2: $resultPart2")
  }
}
