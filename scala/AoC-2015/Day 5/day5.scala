/**
  * Advent of Code 2015 Day 5
  * Link: https://adventofcode.com/2015/day/5
  */

import scala.io.Source
import scala.util.matching.Regex

object day5 {
	def main(args: Array[String]): Unit = {
		val source = Source.fromFile("input.txt")

		/**
		  * Part 1 solution
		  */
		var nice1 = 0

		// Three vowels
		val pattern1 = "(.*[aeiou]){3,}".r

		// Two consecutive characters
		val pattern2 = "([\\w])\\1".r

		// Doesn't contain the patterns
		val pattern3 = "^((?!ab|cd|pq|xy).)*$".r

		/**
		  * Part 2 solution
		  */
		var nice2 = 0
		
		// One letter repeated with another letter in between
		val newPattern1 = "(\\w).\\1".r

		// Pair of any two characters appearing at least twice, without overlap Ex. (aabaa), Not (aaa)
		val newPattern2 = "(\\w\\w).*\\1".r
		
		for (line <- source.getLines()) {
			
			if (!pattern1.findAllIn(line).isEmpty &&
				!pattern2.findAllIn(line).isEmpty && 
				!pattern3.findAllIn(line).isEmpty) 
			{
				nice1 += 1
			}

			if (!newPattern1.findAllIn(line).isEmpty &&
				!newPattern2.findAllIn(line).isEmpty) 
			{
				nice2 += 1
			}
		}
		println("Nice 1: " + nice1)
		println("Nice 2: " + nice2)
		source.close()
	}
}
