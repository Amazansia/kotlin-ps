package silver1

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var abscomparator = compareBy<Int> { kotlin.math.abs(it) }.thenBy { it }
    var pq = PriorityQueue(abscomparator)

    var sb = StringBuilder()

    for (i in 0 until N) {
        var num = readLine().toInt()
        if (num == 0) {
            sb.append("${if (pq.isNotEmpty()) pq.poll() else 0}\n")
        } else {
            pq.add(num)
        }
    }
    print(sb)
}