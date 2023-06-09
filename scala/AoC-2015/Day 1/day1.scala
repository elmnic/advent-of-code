/**
  * Advent of Code 2015 Day 1
  * Link: https://adventofcode.com/2015/day/1
  */

import scala.io.Source

object day1 {
	def main(args: Array[String]): Unit = {
		val source = Source.fromFile("input.txt")

		/**
		  * Part 1 simple solution
		  */
		println(source.mkString.map(c => if (c == '(') 1 else -1).sum)

		/**
		  * Part 2 solution required the knowledge of indices
		  */
		var index = 1
		var floor = 0
		var basementIndex = 0
		for (char <- source.mkString) {
			if (char == '(')
				floor += 1
			else
				floor -= 1
			
			if (floor < 0 && basementIndex == 0)
				basementIndex = index
			index += 1
		}

		println(basementIndex)
	}
}