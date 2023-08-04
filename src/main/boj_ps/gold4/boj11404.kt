package gold4

import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var M = readLine().toInt()

    var bus = Array(N + 1) { IntArray(N + 1) { Int.MAX_VALUE } }
    for (i in 0 until M) {
        var str = readLine().split(" ").map { it.toInt() }
        bus[str[0]][str[1]] = min(bus[str[0]][str[1]], str[2])
//        bus[str[1]][str[0]] = min(bus[str[1]][str[0]], str[2])
    }

    for (i in 1..N) {
        bus[i][i] = 0
    }

    for (k in 1..N) {
        for (i in 1..N) {
            for (j in 1..N) {
                if (bus[i][k] == Int.MAX_VALUE || bus[k][j] == Int.MAX_VALUE) continue
                bus[i][j] = min(bus[i][j], bus[i][k] + bus[k][j])
            }
        }
    }


    var sb = StringBuilder()
    for (i in 1..N) {
        sb.append(bus[i].slice(1..N).map { if (it == Int.MAX_VALUE) 0 else it }.joinToString(" ") + "\n")
    }

    print(sb)
}