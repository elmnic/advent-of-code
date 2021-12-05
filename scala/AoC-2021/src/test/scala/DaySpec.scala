import days.{Day1Spec, Day2Spec, Day3Spec}
import org.scalatest.Sequential

class DaySpec extends Sequential(new Day1Spec, new Day2Spec, new Day3Spec)
