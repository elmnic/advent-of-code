package util

import java.nio.file.{Files, Path, Paths}

trait TestInputHelper {

  private val testDirectory = "src/test/resources"

  def getTestInput(day: String): Either[String, Seq[String]] = {
    val path = Paths.get(s"$testDirectory/day$day.sample")
    if (Files.isRegularFile(path)) {
      getTestCached(day)
    }
    else {
      val filePath = Files.createFile(path)
      Left(
        s"""Sample input does not exist, please copy and paste the sample
         input into "${filePath.toFile}" in the test folder""")
    }
  }

  private def getTestCached(day: String): Either[String, Seq[String]] = {
    val path = Path.of(s"$testDirectory/day$day.sample")
    val sampleInput = Files.readString(path).split("\n").toSeq
    if (sampleInput.mkString.isEmpty) {
      Left(
        s""""${path.toFile}" is empty, please copy and paste the sample input into it""")
    } else {
      Right(sampleInput)
    }
  }

}
