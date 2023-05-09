package silver2

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var q = PriorityQueue<Int>(reverseOrder())

    val N = readLine().toInt()

    for (i in 1..N) {
        var temp = readLine().toInt()
        if (temp == 0) {
            if (q.isEmpty()) bw.write("0\n")
            else bw.write("${q.poll()}\n")
        } else q.add(temp)
    }

    bw.flush()
}