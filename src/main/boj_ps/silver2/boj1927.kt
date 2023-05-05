package silver2

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

    var pq = PriorityQueue<Int>()

    for (i in 0 until N) {
        var num = readLine().toInt()
        if (num == 0) {
            println(if (pq.isNotEmpty()) pq.poll() else 0)
        } else {
            pq.add(num)
        }
    }
}