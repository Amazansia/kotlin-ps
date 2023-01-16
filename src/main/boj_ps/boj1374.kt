import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var savearr = ArrayList<Pair<Int, Int>>()
    var pq = PriorityQueue<Int>()

    for (i in 0 until N) {
        var (num, start, end) = readLine().split(" ").map { it.toInt() }
        savearr.add(Pair(start, end))
    }

    savearr.sortBy { it.first }

    for (i in 0 until N) {
        if (pq.size > 0 && savearr[i].first >= pq.peek()) {
            pq.poll()
        }
        pq.add(savearr[i].second)
    }

    print(pq.size)
}