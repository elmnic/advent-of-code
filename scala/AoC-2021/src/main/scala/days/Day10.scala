package days

import scala.annotation.tailrec
import scala.util.chaining.scalaUtilChainingOps
class Day10 {
  def solve(input: Seq[String]): (Long, Long) = {
    def otherPair(character: Char): Char = character match {
      case ')' => '('
      case '}' => '{'
      case ']' => '['
      case '>' => '<'
      case '(' => ')'
      case '{' => '}'
      case '[' => ']'
      case '<' => '>'
    }

    def points1(character: Char): Int = character match {
      case ')' => 3
      case ']' => 57
      case '}' => 1197
      case '>' => 25137
    }

    def points2(character: Char): Long = character match {
      case ')' => 1L
      case ']' => 2L
      case '}' => 3L
      case '>' => 4L
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

    @tailrec
    def fixIncomplete(row: List[Char], stack: Seq[Char] = Seq.empty): Seq[Char] =
      row match {
        case h :: t if ")}]>".toCharArray.contains(h) => fixIncomplete(t, stack.drop(1))
        case h :: t                                   => fixIncomplete(t, stack.prepended(h))
        case Nil                                      => stack.map(otherPair)
      }

    val resultPart1 = input.map { row =>
      isCorrupt(row.toCharArray.toList)
    }.filter(_._2).map(_._1.pipe(points1)).sum

    val resultPart2 = input
      .map(row => row -> isCorrupt(row.toCharArray.toList))
      .filterNot(_._2._2)
      .map(row => fixIncomplete(row._1.toCharArray.toList).map(points2).foldLeft(0L)((acc, score) => acc * 5L + score))
      .sorted
      .pipe(scores => scores(scores.length / 2))

    (resultPart1, resultPart2)
  }
}
