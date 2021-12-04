import days._

object Main extends App {
  val verbose = args.headOption.flatMap(_.toBooleanOption).getOrElse(false)

  val inputHelper = new InputHelper(verbose)

  Day1.solve(inputHelper.getInput("1"))
  Day2.solve(inputHelper.getInput("2"))
  Day3.solve(inputHelper.getInput("3"))
}
