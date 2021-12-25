package days

class Day9 {
  def solve(input: Seq[String]): (Long, Long) = {

    def getSliding(inputSequence: Seq[String]): Seq[Seq[(Char, String)]] = inputSequence.map(_.sliding(3))
      .map(_.toSeq)
      .map { seq =>
        val first = Seq(seq.head.take(2)).map(s => s.head -> s.drop(1))
        val last = Seq(seq.last.takeRight(2)).map(_.reverse).map(s => s.head -> s.drop(1))
        first ++ seq.map(s => s(1) -> s"${s.head}${s.last}") ++ last.takeRight(2)
      }

    val horizontal = getSliding(input).flatten
    val vertical = getSliding(input.transpose.map(_.mkString)).transpose.flatten
    val resultPart1 = (horizontal zip vertical)
      .map { case (h, v) => h._1 -> (h._2 + v._2)
      }
      .filter { case (value, neighbors) => neighbors.forall(_.toInt > value.toInt) }
      .map(lowPoint => Integer.parseInt(lowPoint._1.toString) + 1)
      .sum

    val resultPart2 = 0

    (resultPart1, resultPart2)
  }
}
