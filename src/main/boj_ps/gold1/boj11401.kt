package gold1

fun main() = with(System.`in`.bufferedReader()) {
    var (N, K) = readLine().split(" ").map { it.toLong() }
    val M = 1_000_000_007

    fun factorial(num: Long): Long {
        var ans = 1L
        var n = num
        while (n > 1) {
            ans = (ans * n) % M
            n--
        }

        return ans
    }

    fun pow(a: Long, b: Long): Long {
        if (b == 1L) {
            return a % M
        }
        var temp: Long = pow(a, b / 2)
        if (b % 2 == 1L) {
            return (((temp * temp) % M) * a) % M
        }
        return (temp * temp) % M
    }

    var A = factorial(N) % M
    var B = factorial(K) * factorial(N - K) % M
    var ans = ((A % M) * pow(B, (M - 2).toLong())) % M

    println(ans)
}