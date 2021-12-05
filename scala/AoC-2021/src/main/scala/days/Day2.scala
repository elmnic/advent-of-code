package days

object Day2 {
  def solve(input: Seq[String]): (Int, Int) = {
    val extractCommand = """(.+) (\d+)""".r
    val resultPart1 = 0
    //      input.map { case extractCommand(direction, units) =>
    //      direction -> units.toInt
    //    }.groupBy(_._1)
    //      .map { case (direction, values) => direction -> values.map(_._2).sum }
    //      .toList
    //      .map {
    //        case ("forward", units) => (units, 0)
    //        case ("down", units) => (0, units)
    //        case ("up", units) => (0, -units)
    //      }
    //      .reduce { case ((x1: Int, y1: Int), (x2: Int, y2: Int)) =>
    //        (x1 + x2, y1 + y2)
    //      } match {
    //      case (x: Int, y: Int) => x * y
    //    }

    val resultPart2 = 0
    println(s"Day 2 - part 1: $resultPart1, part 2: $resultPart2")
    (resultPart1, resultPart2)
  }
}
