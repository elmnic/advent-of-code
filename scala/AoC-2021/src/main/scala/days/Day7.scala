package days

import scala.util.chaining.scalaUtilChainingOps

class Day7 {
  def solve(input: Seq[String]): (Long, Long) = {

    def toFuel(crabs: Seq[Int], position: Int): Int =
      crabs.map(crab => Math.abs(crab - position)).sum

    def toFuel2(crabs: Seq[Int], position: Int): Int =
      crabs.map(crab => Range.inclusive(0, Math.abs(crab - position)).sum).sum

    val startPositions = input.flatMap(_.split(",").map(_.toInt))

    val resultPart1 = startPositions
      .max.pipe(x => Range.inclusive(0, x))
      .map(position => toFuel(startPositions, position))
      .min

    val resultPart2 = startPositions
      .max.pipe(x => Range.inclusive(0, x))
      .map(position => toFuel2(startPositions, position))
      .min

    (resultPart1, resultPart2)
  }
}
