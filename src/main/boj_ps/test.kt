import kotlin.time.*

fun main() = with(System.`in`.bufferedReader()) {
	var tv = TimedValue(Int, 10.toDuration(DurationUnit.MILLISECONDS))

	var test = 1397239847.toDuration(DurationUnit.MILLISECONDS)
	println(test)
	print(test.inWholeHours.toInt())
}