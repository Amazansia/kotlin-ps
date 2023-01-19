fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }
    var arr = BooleanArray(1_000_001) { true }

    arr[0] = false
    arr[1] = false

    for (i in 2..1_000_001) {
        for (j in 2..1_000_001) {
            if (i * j >= arr.size) break
            if (arr[i * j]) {
                arr[i * j] = false
            }
        }
    }

    for (i in N..M) {
        if (arr[i])
            println(i)
    }
}