package gold1

import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    var (min, max) = readLine().split(" ").map { it.toLong() }
    var size = (max - min + 1).toInt()
    var list = MutableList<Long>(size) { -1 }
    var sqrt = sqrt(max.toDouble()).toInt()
    var k1 = MutableList(sqrt + 1) { -1 }
    var k2 = mutableListOf<Long>()

    for (i in 2..sqrt) {
        if (k1[i] == 0) continue
        for (j in i + i..sqrt step i) {
            k1[j] = 0
        }
    }

    for (i in 2..sqrt) {
        if (k1[i] != 0) k2.add(i.toLong())
    }

    for (i in 0 until size) list[i] = min + i

    println(list.toString())

    var count = 0
    for (i in 0 until k2.size) {
        var go: Long = k2[i] * k2[i]
        var temp = min
        var idx = 0L
        if (temp % go != 0L) idx = go - (temp % go)

        for (k in idx until list.size step go)
            list[k.toInt()] = 0
    }

    for (i in 0 until list.size) {
        if (list[i] != 0L)
            count++
    }

    println(list.toString())

    println(count)
}