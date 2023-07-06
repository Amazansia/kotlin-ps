package gold1

fun main() = with(System.`in`.bufferedReader()) {
    var (N, M, K) = readLine().split(" ").map { it.toInt() }
    var arr = LongArray(N)

    for (i in 0 until N) {
        arr[i] = readLine().toLong()
    }

    fun getStoE(s: Long, e: Long) {
        var answer = 0L
        
    }

    var cnt = 0

    while (cnt++ < M + K) {
        var (a, b, c) = readLine().split(" ").map { it.toLong() }
        if (a == 1L) {
            arr[b.toInt() - 1] = c
        } else {
            getStoE(b, c)
        }
    }
}