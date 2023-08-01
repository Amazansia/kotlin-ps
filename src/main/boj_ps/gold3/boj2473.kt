package gold3

import kotlin.math.abs

/*
한번정렬하고
0~N-1까지 순회(N):
차례대로 투포인터 탐색(N)하면서 최적값에 가까운지 체크하기

* */
fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var arr = readLine().split(" ").map { it.toLong() }.toLongArray()

    arr.sort()
//    println(arr.joinToString(" "))
    var now = Long.MAX_VALUE
    var answer = Triple(0L, 0L, 0L)

    for (i in arr.indices) {

        var l = i + 1
        var r = arr.size - 1
//        println(now)

        while (l < r) {
            var sum = arr[l] + arr[r] + arr[i]
            if (abs(sum) < abs(now)) {
                now = abs(sum)
                answer = listOf(arr[i], arr[r], arr[l]).sorted().let { Triple(it[0], it[1], it[2]) }
            }
            if (sum >= 0) {
                r--
            } else {
                l++
            }

//            println("i = $i, l = $l, r = $r, answer = ${answer.first + answer.second + answer.third}")
//            println(abs(arr[l] + arr[r] + arr[i]))
        }
    }

    println(answer.toList().joinToString(" "))
}