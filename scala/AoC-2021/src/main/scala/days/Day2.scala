package days

class Day2 {

  private case class Point(x: Int, y: Int)

  private case class Ship(pos: Point, aim: Int)

  private case class Command(direction: String, units: Int)

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

    val resultPart2 = input.map {
      case extractCommand(direction, units) => Command(direction, units.toInt)
    }.foldLeft(Ship(Point(0, 0), 0))((ship, command) => command match {
      case Command("forward", units) => Ship(Point(ship.pos.x + units, ship.pos.y + units * ship.aim), ship.aim)
      case Command("down", units) => ship.copy(aim = ship.aim + units)
      case Command("up", units) => ship.copy(aim = ship.aim - units)
    }) match {
      case Ship(Point(x, y), _) => x * y
    }

    (resultPart1, resultPart2)
  }
}
