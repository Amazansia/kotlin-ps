package silver3

fun main() = with(System.`in`.bufferedReader()) {
    var (K, L) = readLine().split(" ").map { it.toInt() }
    var log = MutableList(L) { "" }

    for (i in 0 until L) {
        log[i] = readLine()
    }

    log = log.reversed().distinct().toMutableList()
    var sb = StringBuilder()

    for (i in log.size - 1 downTo log.size - K) {
        if (i < 0) break
        sb.append(log[i] + "\n")
    }

    println(sb)
}