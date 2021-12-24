package days

class Day8 {

  case class Numbers(patterns: Seq[String], output: Seq[String])

  def solve(input: Seq[String]): (Long, Long) = {

    def getUnique(patterns: Seq[String]): Seq[String] = patterns.filter { pattern =>
      pattern.length == 2 || pattern.length == 3 || pattern.length == 4 || pattern.length == 7
    }.map(_.sorted)

    def getNonUnique(patterns: Seq[String]): Seq[String] = patterns.filter { pattern =>
      pattern.length != 2 && pattern.length != 3 && pattern.length != 4 && pattern.length != 7
    }.map(_.sorted)

    def deduce(patterns: Seq[String]): Map[Int, String] = {
      val uniqueNumbers: Map[Int, String] = (getUnique(patterns) map { pattern =>
        pattern.length match {
          case 2 => 1 -> pattern
          case 4 => 4 -> pattern
          case 3 => 7 -> pattern
          case 7 => 8 -> pattern
        }
      }).toMap

      def getWithOne(patterns: Seq[String], knownOne: Map[Int, String]): Seq[String] = {
        getNonUnique(patterns).filter { pattern =>
          knownOne(1).forall(pattern.contains(_))
        }
      }

      def deduceZero(patterns: Seq[String]): Map[Int, String] = {
        val uniqueToZero = patterns.flatten.groupBy(identity).filter(_._2.size == 1).keySet.toSeq.head
        patterns.filter(_.contains(uniqueToZero)).map(zeroPattern => 0 -> zeroPattern).toMap
      }

      def deduceThreeAndNine(patterns: Seq[String], knownNumbers: Map[Int, String]): Map[Int, String] = {
        patterns.filterNot(_ == knownNumbers(0)).map { pattern =>
          pattern.length match {
            case 5 => 3 -> pattern
            case 6 => 9 -> pattern
          }
        }.toMap
      }

      def deduceTwoFiveSix(patterns: Seq[String], knownNumbers: Map[Int, String]): Map[Int, String] = {
        val remainingNumbers = patterns diff knownNumbers.values.toSeq
        val uniqueTo = remainingNumbers.flatten.groupBy(identity).map(kv => {
          kv._1 -> kv._2.length
        }).filter(_._2 == 1).keySet.toSeq.head

        val knownTwo = Map(2 -> remainingNumbers.find(_.contains(uniqueTo)).get)

        knownTwo ++ remainingNumbers.filterNot(_ == knownTwo(2)).map { pattern =>
          pattern.length match {
            case 5 => 5 -> pattern
            case 6 => 6 -> pattern
          }
        }.toMap
      }


      val withZero = deduceZero(getWithOne(patterns, uniqueNumbers)) ++ uniqueNumbers
      val withZeroThreeNine = deduceThreeAndNine(
        getWithOne(patterns, uniqueNumbers), withZero) ++ withZero
      val allKnown = deduceTwoFiveSix(patterns.map(_.sorted), withZeroThreeNine) ++ withZeroThreeNine
      allKnown
    }

    val numbers = input.map { case s"$tenNumbers | $fourNumbers" => Numbers(
      tenNumbers.split(" ").toSeq, fourNumbers.split(" ").toSeq)
    }

    val resultPart1 = numbers.flatMap(numbers => getUnique(numbers.output)).size
    val resultPart2 = numbers.map { numbers =>
      val segmentMap = deduce(numbers.patterns).map { case (k, v) => v.sorted -> k }
      Integer.parseInt(numbers.output.map(_.sorted).map(segmentMap(_)).mkString)
    }.sum

    (resultPart1, resultPart2)
  }
}
