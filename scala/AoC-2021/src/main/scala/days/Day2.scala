package days

class Day2 {

  private case class Point(x: Int, y: Int)

  def solve(input: Seq[String]): (Int, Int) = {

    val extractCommand = """(.+) (\d+)""".r

    val resultPart1 =
      input.map { case extractCommand(direction, units) =>
        direction -> units.toInt
      }.groupBy(_._1)
        .map { case (direction, values) => direction -> values.map(_._2).sum }
        .toList
        .map {
          case ("forward", units) => Point(units, 0)
          case ("down", units) => Point(0, units)
          case ("up", units) => Point(0, -units)
        }
        .reduce {
          (point1, point2) =>
            Point(point1.x + point2.x, point1.y + point2.y)
        } match {
        case Point(x, y) => x * y
      }

    val resultPart2 = 0
    (resultPart1, resultPart2)
  }
}
