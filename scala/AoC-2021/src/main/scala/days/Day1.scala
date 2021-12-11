package days

class Day1 {
  def solve(input: Seq[String]): (Int, Int) = {

    val resultPart1 = input
      .map(_.toInt)
      .sliding(2)
      .collect { case Seq(a1, a2) =>
        if (a1 < a2) 1 else 0
      }
      .sum

    val resultPart2 = input
      .map(_.toInt)
      .sliding(3)
      .map(_.sum)
      .sliding(2)
      .collect { case Seq(a1, a2) =>
        if (a1 < a2) 1 else 0
      }
      .sum

    (resultPart1, resultPart2)
  }
}
