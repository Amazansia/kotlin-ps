package silver3

fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().trim().split(" ").map { it.toInt() }

    var arr = readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    arr.sort()

    // now: arr의 방문 인덱스
    fun dfs(now: Int, array: IntArray, count: Int) {
        if (count == M) {
            println(array.joinToString(" "))
            return
        }

        for (i in 0 until N) {
            if (arr[now] > arr[i]) {
                continue
            }
            array[count] = arr[i]
            dfs(i, array, count + 1)
        }
    }

    dfs(0, IntArray(M), 0)
}