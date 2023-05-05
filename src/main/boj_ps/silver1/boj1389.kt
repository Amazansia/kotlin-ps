package silver1

fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().split(" ").map { it.toInt() }

    var nodes = Array(N) { IntArray(N) { 987654321 } }
    for (i in 0 until N) {
        nodes[i][i] = 0
    }

    for (i in 0 until M) {
        var (s, e) = readLine().split(" ").map { it.toInt() }
        s--
        e--
        nodes[s][e] = 1
        nodes[e][s] = 1
    }

    for (k in 0 until N) {
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (nodes[i][k] + nodes[k][j] < nodes[i][j])
                    nodes[i][j] = nodes[i][k] + nodes[k][j]
            }
        }
    }

    var num = Int.MAX_VALUE
    var answer = 0
    for (i in 0 until N) {
        var sum = 0
        for (j in 0 until N) {
            sum += nodes[i][j]
        }
        if (num > sum) {
            num = sum
            answer = i + 1
        }
    }

    print(answer)
}