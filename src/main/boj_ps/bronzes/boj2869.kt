package bronzes

import kotlin.math.ceil

fun main() = with(System.`in`.bufferedReader()) {
    var (A, B, V) = readLine().split(" ").map { it.toLong() }
    V -= A
    print((ceil((V / (A - B).toDouble())) + 1).toInt())
}