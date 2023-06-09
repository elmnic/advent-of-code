/**
  * Advent of Code 2015 Day 3
  * Link: https://adventofcode.com/2015/day/3
  */

import scala.io.Source

object day3 {

	class Point() {
		var x: Int = 0
		var y: Int = 0
		def move(dir: Char): Unit = dir match {
			case '<' => x += -1
			case '>' => x += 1
			case '^' => y += 1
			case 'v' => y += -1
		}
	}

	def main(args: Array[String]): Unit = {
		var santaPoint = new Point()
		var roboPoint = new Point()
		var deliveryMap = scala.collection.mutable.Map[(Int, Int), Int]((santaPoint.x, santaPoint.y) -> 1)
		val source = Source.fromFile("input.txt")

		for (dir <- source.mkString.grouped(2)) {
			santaPoint.move(dir(0))
			deliveryMap += ((santaPoint.x, santaPoint.y) -> 1)
			roboPoint.move(dir(1))
			deliveryMap += ((roboPoint.x, roboPoint.y) -> 1)
		}
		println("Houses: " + deliveryMap.size)
	}
}