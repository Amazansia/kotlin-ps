package silver3

fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().trim().split(" ").map { it.toInt() }

    fun dfs(now: Int, str: String) {
        if (now == M) {
            println(str)
            return
        }
        var last = str.last().code - '0'.code

        for (i in 1..N) {
            if (i < last) {
                continue
            }
            dfs(now + 1, "$str $i")
        }
    }

    for (i in 1..N) {
        dfs(1, "$i")
    }
}