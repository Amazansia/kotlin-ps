package silver1

fun main() = with(System.`in`.bufferedReader()) {
    var (A, B, C) = readLine().split(" ").map { it.toLong() }

    var k: Long

    fun recursion(num: Long): Long {
        if (num == 0L) return 1
        if (num == 1L) return A % C

        k = recursion(num / 2) % C
        if (num % 2 == 0L) return k * k % C
        return k * k % C * A % C
    }

    println(recursion(B))
}