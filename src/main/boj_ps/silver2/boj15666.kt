package silver2

fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().split(" ").map { it.toInt() }
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()
    arr.sort()

    var answerSet = mutableSetOf<String>()

    fun dfs(now: Int, count: Int, answer: IntArray) {
        if (count == M) {
            answerSet.add(answer.joinToString(" "))
            return
        }

        for (i in arr) {
            if (now > i) continue
            answer[count] = i
            dfs(i, count + 1, answer)
        }
    }

    dfs(0, 0, IntArray(M))

    answerSet.forEach {
        println(it)
    }
}