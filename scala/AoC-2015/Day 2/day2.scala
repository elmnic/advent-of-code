/**
  * Advent of Code 2015 Day 2
  * Link: https://adventofcode.com/2015/day/2
  */

import scala.io.Source

object day2 {
	def main(args: Array[String]): Unit = {
		val source = Source.fromFile("input.txt")
		
		var paper = 0
		var paperIfCombinationsWasDumber = 0

		var ribbon = 0
		var paperCounter = 0
		for (line <- source.getLines()) {
			val numbers = line.split('x').toList.map(_.toInt)
			/**
			  * Not really the Scala-way
			  */
			val l = numbers(0)
			val w = numbers(1)
			val h = numbers(2)
			val smallestSide = List(l*w, l*h, w*h).min
			val shortestSide = List(l, w, h).sorted.take(2)
			paper += 2 * (l*w + w*h + h*l) + smallestSide

			/**
			 * This doesn't work
			 * combinations skips duplicate combinations, 
			 * e.g. (xyy) should give (xy xy yy) but only gives (xy yy) because xy is already present
			*/
			val areas = numbers.combinations(2).toList.map(_.product)
			paperIfCombinationsWasDumber += 2 * areas.sum + areas.min

			// Part 2
			ribbon += 2 * numbers.sorted.take(2).sum + numbers.product

		}
		source.close()


		println("Paper 1 result: " + paper)
		println("Paper 2 result: " + paperIfCombinationsWasDumber)
		println("Ribbon: " + ribbon)
	}
}