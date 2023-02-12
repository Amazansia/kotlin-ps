import kotlin.time.*

@OptIn(ExperimentalTime::class)
fun main() = with(System.`in`.bufferedReader()) {
    //var tv = TimedValue(Int, 10.toDuration(DurationUnit.MILLISECONDS))

    var test = 1397239847.toDuration(DurationUnit.MILLISECONDS)
    println(test)
    print(test.inWholeHours.toInt())
}