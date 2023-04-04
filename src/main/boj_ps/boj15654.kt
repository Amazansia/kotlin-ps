fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().trim().split(" ").map { it.toInt() }

    var arr = readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    arr.sort()
    var visited = BooleanArray(N)

    fun dfs(now: Int, list: MutableList<Int>) {

        if (list.size == M) {
            println(list.joinToString(" "))
            return
        }

        visited[now] = true

        for (i in 0 until N) {
            if (visited[i]) {
                continue
            }
            var newlist = mutableListOf<Int>()
            newlist.addAll(list)
            newlist.add(arr[i])
            visited[i] = true
            dfs(i, newlist)
            visited[i] = false
        }
    }

    for (i in 0 until N) {
        visited = BooleanArray(N)
        dfs(i, mutableListOf(arr[i]))
    }
}