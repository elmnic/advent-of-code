package days

import scala.annotation.tailrec
import scala.util.chaining.scalaUtilChainingOps
class Day10 {
  def solve(input: Seq[String]): (Long, Long) = {
    def otherPair(character: Char) = character match {
      case ')' => '('
      case '}' => '{'
      case ']' => '['
      case '>' => '<'
      case '(' => ')'
      case '{' => '}'
      case '[' => ']'
      case '<' => '>'
    }

    def points1(character: Char) = character match {
      case ')' => 3
      case ']' => 57
      case '}' => 1197
      case '>' => 25137
    }

    def points2(character: Char) = character match {
      case ')' => 1
      case ']' => 2
      case '}' => 3
      case '>' => 4
    }

    @tailrec
    def isCorrupt(row: List[Char], stack: Seq[Char] = Seq.empty): (Char, Boolean) =
      row match {
        case h :: t if ")}]>".toCharArray.contains(h) && stack.isEmpty              => (h, true)
        case h :: t if ")}]>".toCharArray.contains(h) && stack.head != otherPair(h) => (h, true)
        case h :: t if ")}]>".toCharArray.contains(h)                               => isCorrupt(t, stack.drop(1))
        case h :: t                                                                 => isCorrupt(t, stack.prepended(h))
        case Nil                                                                    => (' ', false)
      }

    val resultPart1 = input.map { row =>
      isCorrupt(row.toCharArray.toList)
    }.filter(_._2).map(_._1.pipe(points1)).sum

    val resultPart2 = 0

    (resultPart1, resultPart2)
  }
}
