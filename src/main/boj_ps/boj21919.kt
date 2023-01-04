import java.util.Collections.max
import kotlin.math.max


fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    // 아래 arr에서 .toSet() 이것만 빼도 틀렸습니다 뜸
    var arr = readLine().split(" ").map { it.toInt() }.toSet()
    var M = max(arr)
    var primearr = BooleanArray(max(arr) + 1) { true }
    primearr[0] = false
    primearr[1] = false

    // 소수의 최소공배수
    // 1. 소수를 구한다 (2 ~ 1000000)
    // 2. 최소공배수를 구한다 (답은 2^63 (???)) - 구한 소수를 모두 곱하면 됨

    val set = HashSet<Int>()

    // 소수 판정: 에라토스테네스의 체
    for (i in 2..M) {
        for (j in 2..M) {
            if (i * j > M) break
            if (i * j <= M && primearr[i * j]) {
                //println(i * j)
                primearr[i * j] = false
            }
        }
    }

    var cal: Long = 1
    arr.forEach {
        if (primearr[it]) {
            // println(it)
            cal *= it
            primearr[it] != primearr[it]
        }
    }
    println(if (cal == 1.toLong()) -1 else cal)

    // 재밌네...
    // 여기서 set을 쓰지 않으면, 즉 BooleanArray로 중복 여부를 판단하면 다음의 반례가 생김: 5\n2 2 2 2 2 answer=2
    // 같은 소수가 중복으로 들어올 경우 걸러내기 어려움
}