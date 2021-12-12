package days

class Day5 {


  case class Point(x: Int, y: Int)

  case class Line(start: Point, end: Point) {
    def isDiagonal: Boolean = !(start.x == end.x || start.y == end.y)
  }

  case class Board(points: Seq[Seq[Point]])

  def solve(input: Seq[String]): (Long, Long) = {

    def getRange(start: Int, end: Int) = Range.inclusive(start, end, if (start > end) -1 else 1)

    def pointsCoveredByLines(lines: Seq[Line]): Seq[Point] = lines
      .flatMap { line =>
        val xRange = getRange(line.start.x, line.end.x)
        val yRange = getRange(line.start.y, line.end.y)
        if (line.isDiagonal) {
          for {
            (x, y) <- xRange zip yRange
          } yield Point(x, y)
        } else {
          for {
            x <- xRange
            y <- yRange
          }
          yield Point(x, y)
        }
      }

    def countOverlap(points: Seq[Point]): Int = points.foldLeft[Map[Point, Int]](Map.empty) {
      case (acc, point) => if (acc contains point) {
        acc - point + (point -> (acc(point) + 1))
      } else {
        acc + (point -> 1)
      }
    }.count(_._2 > 1)

    val inputLines = input
      .map { case s"$x1,$y1 -> $x2,$y2" =>
        Line(Point(x1.toInt, y1.toInt), Point(x2.toInt, y2.toInt))
      }

    val straightLines = inputLines
      .filterNot(_.isDiagonal)

    val straightPoints = pointsCoveredByLines(straightLines)
    val resultPart1 = countOverlap(straightPoints)

    val allPoints = pointsCoveredByLines(inputLines)
    val resultPart2 = countOverlap(allPoints)

    (resultPart1, resultPart2)
  }
}
