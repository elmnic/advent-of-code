/**
  * Advent of Code 2015 Day 4
  * Link: https://adventofcode.com/2015/day/4
  */

import scala.io.Source

object day4 {

	def hash(s: String) = {
		val m = java.security.MessageDigest.getInstance("MD5")
		val b = s.getBytes("UTF-8")
		m.update(b, 0, b.length)
		new java.math.BigInteger(1, m.digest()).toString(16).reverse.padTo(32, "0").reverse.mkString
	}

	def main(args: Array[String]): Unit = {
		val source = Source.fromFile("input.txt").mkString
		
		var i = 0
		var found5 = false
		var found6 = false
		var result = scala.collection.mutable.Map[String, Int]()
		var h = ""
		
		while (!found5 || !found6) {
			i += 1
			h = hash(source + i)
			
			if (!found5 && h.take(5).toString == "00000") {
				result += (h -> i)
				found5 = true
			}
			
			if (!found6 && h.take(6).toString == "000000") {
				result += (h -> i)
				found6 = true
			}

			if (i % 50000 == 0)
				println(i)
		}
		
		result.map(println)
	}
}
