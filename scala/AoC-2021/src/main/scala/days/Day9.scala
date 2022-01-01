package days

import scala.annotation.tailrec

class Day9 {

  case class Point(x: Int, y: Int, value: String, idx: Int)

  def solve(input: Seq[String]): (Long, Long) = {

    // Globals that are needed in every method
    val xMax = input.head.length
    val yMax = input.size
    val matrix = input.map(_.split("").toSeq.zipWithIndex).zipWithIndex.flatMap { case (row, yIdx) =>
      row.map { case (value, xIdx) => Point(xIdx, yIdx, value, xIdx + yIdx * xMax) }
    }

    def part1Old(part1Input: Seq[String]) = {
      def getSliding(inputSequence: Seq[String]): Seq[Seq[(Char, String)]] =
        inputSequence.map(_.sliding(3).toSeq).map { seq =>
          val first = Seq(seq.head.take(2)).map(s => s.head -> s.drop(1))
          val last = Seq(seq.last.takeRight(2)).map(_.reverse).map(s => s.head -> s.drop(1))
          first ++ seq.map(s => s(1) -> s"${s.head}${s.last}") ++ last.takeRight(2)
        }

      val horizontal = getSliding(part1Input).flatten
      val vertical = getSliding(part1Input.transpose.map(_.mkString)).transpose.flatten
      (horizontal zip vertical).map { case (h, v) => h._1 -> (h._2 + v._2) }.filter { case (value, neighbors) =>
        neighbors.forall(_.toInt > value.toInt)
      }.map(lowPoint => Integer.parseInt(lowPoint._1.toString) + 1).sum
    }

    def pointNeighbors(point: Point): Seq[Point] = {
      val rowLeft = point match {
        case Point(x, _, _, idx) if x != 0 => Seq(matrix(idx - 1 % xMax))
        case _                             => Seq.empty
      }
      val rowRight = point match {
        case Point(x, _, _, idx) if x != xMax - 1 => Seq(matrix(idx + 1 % xMax))
        case _                                    => Seq.empty
      }
      val colUp = point match {
        case Point(_, y, _, idx) if y != 0 => Seq(matrix(idx - xMax))
        case _                             => Seq.empty
      }
      val colDown = point match {
        case Point(_, y, _, idx) if y != yMax - 1 => Seq(matrix(idx + xMax))
        case _                                    => Seq.empty
      }

      Seq(rowLeft, rowRight, colUp, colDown).flatten
    }

    def findMinPoints(matrix: Seq[Point]): Seq[Point] =
      matrix.map { point =>
        val lowPoint = pointNeighbors(point).forall(_.value > point.value)
        (point, lowPoint)
      }.filter(_._2).map(_._1)

    @tailrec
    def expandBasin(startPoint: Point, closed: Seq[Point] = Seq.empty, open: Seq[Point] = Seq.empty): Seq[Point] =
      startPoint match {
        case Point(_, _, "9", _) if open.isEmpty  => closed
        case _ if open.isEmpty && closed.nonEmpty => closed
        case Point(_, _, "9", _)                  => expandBasin(open.head, closed :+ open.head, open.drop(1))
        case point =>
          pointNeighbors(point).filterNot(closed.contains(_)) match {
            case Seq(neighborHead, neighborTail @ _*) =>
              expandBasin(neighborHead, closed :+ point, open.concat(neighborTail))
            case Seq() => expandBasin(open.head, closed :+ point, open.drop(1))
          }
      }

    def findBasins(minPoint: Point): Seq[Point] =
      expandBasin(minPoint).distinctBy(_.idx).filter(_.value != "9")

    val resultPart1 = findMinPoints(matrix).map(_.value.toInt + 1).sum
    val resultPart2 = findMinPoints(matrix).map { minPoint =>
      findBasins(minPoint).size
    }.sorted.reverse.take(3).product

    (resultPart1, resultPart2)
  }
}
