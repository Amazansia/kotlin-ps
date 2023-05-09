package gold1

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

    var work = Array(N) { Pair(0, 0.0) }
    repeat(N) {
        var str = readLine().split(" ")
        work[it] = Pair(it + 1, str[0].toDouble() / str[1].toDouble())
    }
    val sb = StringBuilder()
    work.sortedWith(compareBy<Pair<Int, Double>> { it.second }.thenBy { it.first })
        .forEach { sb.append("${it.first} ") }

    print(sb)
}