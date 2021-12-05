package days

object Day1 {
  def solve(input: Seq[String]): (Int, Int) = {

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

    println(s"Day 1\n\tpart 1: $resultPart1\n\tpart 2: $resultPart2")
    (resultPart1, resultPart2)
  }
}
