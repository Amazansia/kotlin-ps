import java.util.Collections.max
import kotlin.math.max


fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var arr = readLine().split(" ").map { it.toInt() }
    var M = max(arr)
    var primearr = BooleanArray(max(arr) + 1) { true }
    primearr[0] = false
    primearr[1] = false

    // 소수의 최소공배수
    // 1. 소수를 구한다 (2 ~ 1000000)
    // 2. 최소공배수를 구한다 (답은 2^63 (???)) - 구한 소수를 모두 곱하면 됨

    // 소수 판정: 에라토스테네스의 체
    for (i in 2..M) {
        for (j in 1..M) {
            if (i * j > M) break
            if (i * j <= M && primearr[i * j]) {
                primearr[i * j] = false
            }
        }
    }

    var cal: Long = 1
    arr.forEach {
        if (primearr[it]) {
            cal *= it
            primearr[it] != primearr[it]
        }
    }
    println(if (cal == 1.toLong()) -1 else cal)

}