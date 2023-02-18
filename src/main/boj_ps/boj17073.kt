fun main() = with(System.`in`.bufferedReader()) {
    var answer = 0
    var (N, M) = readLine().split(" ").map { it.toInt() }
    var tree = Array<ArrayList<Int>>(N + 1) { ArrayList() }
    for (i in 0 until N - 1) {
        var (U, V) = readLine().trim().split(" ").map { it.toInt() }
        tree[U].add(V)
        tree[V].add(U)
    }
    for (i in 2..N) {
        if (tree[i].size == 1) answer++
    }
    print("${M / answer.toDouble()}")
}