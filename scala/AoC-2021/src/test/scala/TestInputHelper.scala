import java.nio.file.{Files, Path, Paths}

object TestInputHelper {

  private val testDirectory = "src/test/resources"

  def getTestInput(day: String): Either[String, Seq[String]] = {
    if (Files.isRegularFile(Paths.get(s"$testDirectory/day$day.sample"))) {
      Right(getTestCached(day))
    }
    else {
      val filePath = Files.createFile(Paths.get(s"$testDirectory/day$day.sample"))
      Left(s"""Sample input does not exist, please copy and paste the input into "${filePath.toFile}" in the test folder""")
    }
  }

  private def getTestCached(day: String): Seq[String] =
    Files.readString(Path.of(s"$testDirectory/day$day.sample")).split("\n").toSeq

}
