package days

class Day5 {

  case class Point(x: Int, y: Int)

  case class Line(start: Point, end: Point)

  def solve(input: Seq[String]): (Int, Int) = {

    val lines = input
      .map { case s"$x1,$y1 -> $x2,$y2" => Line(Point(x1.toInt, y1
        .toInt), Point(x2.toInt, y2.toInt))
      }

    val resultPart1 = 0

    val resultPart2 = 0

    (resultPart1, resultPart2)
  }
}
