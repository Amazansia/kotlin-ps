package gold3

import java.util.*

/*
누가봐도 위상정렬
순서겹치면...
사이클?
* */

fun main() {

    var (N, M) = readln().split(" ").map { it.toInt() }
    var arr = Array(N + 1) { mutableListOf<Int>() }

    var degree = IntArray(N + 1)

    for (i in 0 until M) {
        var str = readln().trim().split(" ").map { it.toInt() }
        for (j in 1 until str.size - 1) {
            arr[str[j]].add(str[j + 1])
            degree[str[j + 1]]++
        }
    }

    fun TS(): String {
        var q: Queue<Int> = LinkedList()
        var res = mutableListOf<Int>()

        for (i in 1..N) {
            if (degree[i] == 0) q.offer(i)
        }

        while (q.isNotEmpty()) {
            var now = q.poll()
            res.add(now)

            for (next in arr[now]) {
                degree[next]--
                if (degree[next] == 0) q.offer(next)
            }
        }

        if (res.size != N) return "0"

        var sb = StringBuilder()
        for (i in res.indices) {
            sb.append(res[i].toString() + "\n")
        }

        return sb.toString()
    }

    println(TS())
}