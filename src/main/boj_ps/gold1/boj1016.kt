package gold1

import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    var (min, max) = readLine().split(" ").map { it.toLong() }
    var size = (max - min + 1).toInt()
    var list = MutableList<Long>(size) { -1 }
    var sqrt = sqrt(max.toDouble()).toInt()
    var k1 = MutableList(sqrt + 1) { -1 }
    var k2 = mutableListOf<Long>()

}