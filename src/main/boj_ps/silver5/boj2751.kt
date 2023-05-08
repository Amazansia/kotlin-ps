package silver5

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var q = PriorityQueue<Int>()
    for (i in 0 until N) {
        q.add(readLine().toInt())
    }
    var sb = StringBuilder()
    while (q.isNotEmpty()) {
        sb.append("${q.poll()}\n")
    }
    print(sb)
}