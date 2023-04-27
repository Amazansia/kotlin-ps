package silver2

fun main() = with(System.`in`.bufferedReader()) {
    var (A, B) = readLine().split(" ").map { it.toLong() }
    var answer = Long.MAX_VALUE
    fun dfs(num: Long, count: Long) {
        if (num > B) {
            return
        }
        if (num == B) {
            answer = kotlin.math.min(answer, count)
        }

        dfs(num * 2, count + 1)
        dfs(num * 10 + 1, count + 1)
    }

    dfs(A, 0)

    println(if (answer == Long.MAX_VALUE) -1 else answer + 1)
}