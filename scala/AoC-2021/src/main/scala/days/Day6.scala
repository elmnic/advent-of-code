package days

import scala.annotation.tailrec

class Day6 {

  case class Fish(amount: Long, timer: Int)

  def solve(input: Seq[String]): (Long, Long) = {

    def spawnNewFish(fishes: Seq[Fish]): Seq[Fish] = {
      val newFish: Seq[Fish] = fishes.find(_.timer == 0).map { fish =>
        Seq(Fish(fish.amount, 8))
      }.getOrElse(Seq.empty)

      val oldFish = fishes.map {
        case Fish(amount, 0) => Fish(amount, 6)
        case fish            => fish.copy(timer = fish.timer - 1)
      }.groupBy(_.timer).map {
        case (timer, fishes) => Fish(fishes.map(_.amount).sum, timer)
      }.toSeq
      oldFish ++ newFish
    }

    @tailrec
    def updateTimers(fishes: Seq[Fish], timerLimit: Int): Seq[Fish] = timerLimit match {
      case 0 => fishes
      case _ => updateTimers(spawnNewFish(fishes), timerLimit - 1)
    }

    val fishes = input
      .flatMap(_.split(",").map(_.toInt))
      .groupBy(identity)
      .map { case (timer, fishes) => Fish(fishes.length.toLong, timer) }.toSeq

    val resultPart1 = updateTimers(fishes, 80).map(_.amount).sum
    val resultPart2 = updateTimers(fishes, 256).map(_.amount).sum

    (resultPart1, resultPart2)
  }
}
