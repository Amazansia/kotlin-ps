package silver5

fun main() = with(System.`in`.bufferedReader()) {
    var (N, K) = readLine().split(" ").map { it.toInt() }
    var arr = Array(N) { IntArray(4) }
    for (i in 0 until N) {
        arr[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    var answer = 0
    var a = arr.find { it[0] == K } ?: arr[0]

    for (i in 0 until N) {
        if (arr[i][1] > a[1]) {
            answer++
            continue
        }
        if (arr[i][1] >= a[1] && arr[i][2] > a[2]) {
            answer++
            continue
        }
        if (arr[i][1] >= a[1] && arr[i][2] >= a[2] && arr[i][3] > a[3]) {
            answer++
            continue
        }
    }
    println(answer + 1)
}