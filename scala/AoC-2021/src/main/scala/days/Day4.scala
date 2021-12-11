package days

import scala.annotation.tailrec
import scala.util.chaining.scalaUtilChainingOps

class Day4 {

  case class MarkableNumber(number: Int, marked: Boolean)

  case class Board(numbers: Seq[MarkableNumber], bingoNumber: Int = 0) {
    def turnsToBingo: Int = numbers.count(_.marked)

    def unmarkedSum: Int = numbers.filterNot(_.marked).map(_.number).sum

    def markedSum: Int = numbers.filter(_.marked).map(_.number).sum

    def printBoard(): Unit = numbers
      .map(_.number)
      .grouped(5)
      .foreach(row => println(row.mkString(" ")))
      .tap(_ => println())

    def printMarked(): Unit = numbers
      .map(_.marked)
      .grouped(5)
      .foreach(row => println(row.mkString(" ")))
      .tap(_ => println())
  }

  def solve(input: Seq[String]): (Int, Int) = {
    def markNumbers(board: Board, numberToMark: Int): Board = {
      Board(board.numbers.map {
        case MarkableNumber(number, mark) =>
          MarkableNumber(number, number == numberToMark || mark)
      })
    }

    @tailrec
    def markBoards(numbers: Seq[Int], board: Board): Board = numbers match {
      case Nil         => board
      case number +: t =>
        val newBoard = markNumbers(board, number)
        if (isBingo(newBoard)) {
          newBoard.copy(bingoNumber = number)
        } else {
          markBoards(t, newBoard)
        }
    }

    def isBingo(board: Board): Boolean = {
      val bingoMatrix = board.numbers.map(_.marked).grouped(5).toSeq
      val rowsWithBingo = bingoMatrix.map(_.reduce(_ && _))
      val colsWithBingo = bingoMatrix.transpose.map(_.reduce(_ && _))
      (rowsWithBingo ++ colsWithBingo).contains(true)
    }

    val numbersToDraw = input.head.split(",").map(_.toInt).toSeq

    val boards: Seq[Board] = input
      .tail
      .filter(_.nonEmpty)
      .grouped(5)
      .map { groupOfRows =>
        Board(groupOfRows.flatMap { rowAsString =>
          rowAsString.split(""" +""")
                     .filterNot(_.isEmpty)
                     .map(number => MarkableNumber(number
                       .toInt, marked = false))
        })
      }.toSeq


    val markedBoards: Seq[Board] = boards.map(markBoards(numbersToDraw, _))

    val winningBoard = markedBoards
      .minBy(board => numbersToDraw.indexOf(board.bingoNumber))
    val resultPart1 = winningBoard.bingoNumber * winningBoard.unmarkedSum

    val losingBoard = markedBoards
      .maxBy(board => numbersToDraw.indexOf(board.bingoNumber))
    val resultPart2 = losingBoard.bingoNumber * losingBoard.unmarkedSum

    (resultPart1, resultPart2)
  }
}
