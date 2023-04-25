fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().split(" ").map { it.toInt() }
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()
    arr.sort()

    var visited = BooleanArray(arr.size)
    var answerSet = mutableSetOf<List<Int>>()

    fun dfs(len: Int, combination: IntArray) {
        if (len == M) {
            answerSet.add(combination.toList())
            return
        }
        for (i in arr.indices) {
            if (visited[i]) continue
            combination[len] = arr[i]
            visited[i] = true
            dfs(len + 1, combination)
            visited[i] = false
        }
    }

    dfs(0, IntArray(M))

    answerSet.forEach {
        println(it.joinToString(" "))
    }
}