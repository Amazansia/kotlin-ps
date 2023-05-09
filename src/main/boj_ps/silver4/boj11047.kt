package silver4

fun main() = with(System.`in`.bufferedReader()) {
    var (N, K) = readLine().split(" ").map { it.toLong() }
    var arr = IntArray(N.toInt())
    for (i in arr.indices) {
        arr[i] = readLine().toInt()
    }

    var answer = 0

    for (i in arr.size - 1 downTo 0) {
        answer += (K / arr[i]).toInt()
        K %= arr[i]
    }

    print(answer)
}