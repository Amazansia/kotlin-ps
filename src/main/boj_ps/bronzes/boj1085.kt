package bronzes

import kotlin.math.abs
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val (x, y, w, h) = readLine().split(" ").map { it.toInt() }
    print(
        min(min(abs(x - w), abs(y - h)), min(x, y))
    )
}