fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map { it.toInt() }
    val arr = BooleanArray(N + 1) { true }
    arr[0] = false
    arr[1] = false
    var cnt = 0

    for (i in 2..N) {
        for (j in 1..N) {
            if (i * j <= N && arr[i * j]) {
                arr[i * j] = false
                cnt++
                if (cnt == K) {
                    print(i * j)
                    return
                }
            }
        }
    }
}