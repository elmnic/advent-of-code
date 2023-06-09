/**
  * Advent of Code 2015 Day 13
  * Link: https://adventofcode.com/2015/day/13
  */

import scala.io.Source

object day13 {
	def main(args: Array[String]): Unit = {

		val happyMap = scala.collection.mutable.Map[(String, String), Int]()
		var uqNames = scala.collection.mutable.Set[String]()
		val source = Source.fromFile("input.txt")
		
		// Read input and store names and values
		for (line <- source.getLines()) {
			val split = line.replace("lose ", "-")
							.replace(".", "")
							.replace("gain ", "")
							.split(" ")
			val name1 = split(0)
			val name2 = split(9)
			val happiness = split(2).toInt

			// Store unique names
			uqNames += name1

			// Store happiness value for each name combination
			happyMap += ((name1, name2) -> happiness)
		}
		source.close()

		/**
		 * Part 2
		 * Add "myself" to the list of guests
		  */
		for (guest <- uqNames) {
			happyMap += (("Myself", guest) -> 0)
			happyMap += ((guest, "Myself") -> 0)
		}
		uqNames += "Myself"

		// Acquire the unique non-cyclical permutations for all possible seatings
		var permutations = uqNames.toList.slice(1, uqNames.size).permutations
		var seatingHappiness = Map[List[String], Int]()


		// Loop through all unique permutations and calculate total happiness value
		for (perm <- permutations) {

			val seating = List(uqNames.head) ::: perm ::: List(uqNames.head)
			var value = 0

			for (i <- 1 until seating.length)
				value += happyMap((seating(i-1), seating(i))) + happyMap((seating(i), seating(i-1)))

			// Store the seating and it's value
			seatingHappiness += (seating.slice(0, seating.length-1) -> value)
		}

		// Retrieve the maximum value
		println("Maximum happiness seating: \n" + seatingHappiness.maxBy{_._2})

		/**
		 * Because there is a cycle the possible permutations are reduced by a factor of (n-1)
		 * 8 guests gives 8! = 40 320 permutations, one eighth of those are duplicated through cycles
		 * The actual number of unique permutations is (8-1)! = 5040
		 * The first seating can remain the same and then the rest of
		 * the seats are just permutations of the remaining guests, e.g.
		 * 
		 * 1   2 3 4
		 * 1   3 4 2
		 * 1   4 2 3
		 * 1   2 4 3
		 * 1   4 3 2
		 * 1   3 2 4
		 */
	}
}
