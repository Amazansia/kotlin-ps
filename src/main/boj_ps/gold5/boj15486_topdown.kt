package gold5

import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var time = IntArray(N + 2)
    var pay = IntArray(N + 2)
    for (i in 1..N) {
        var input = readLine().split(" ").map { it.toInt() }
        time[i] = input[0]
        pay[i] = input[1]
    }

    // 뒤에서부터 채워 나가는 방식, 하향식
    for (i in N downTo 1) {
        if (N + 1 < i + time[i]) {
            pay[i] = pay[i + 1]
            continue
        }
        pay[i] = max(pay[i + 1], pay[i] + pay[i + time[i]])
    }

    print(pay[1])
}