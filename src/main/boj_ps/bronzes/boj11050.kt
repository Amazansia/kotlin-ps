package bronzes

fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map { i -> i.toInt() }
    var sum: Long = 1
    var repeatnum = 1
    for (i in K + 1..N) {
        sum *= i
        sum /= repeatnum
        repeatnum++
    }

    println(sum)
}