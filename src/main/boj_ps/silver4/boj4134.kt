package silver4

fun main() = with(System.`in`.bufferedReader()) {
    val T = readLine().toInt()

    fun isPrime(N: Int): Boolean {
        var sq = kotlin.math.sqrt(N.toDouble()).toInt()
        while (N % sq != 0 && sq > 0) {
            sq--
        }
        return sq != 0
    }
    repeat(T) {
        var N = readLine().toInt() + 1
        while (!isPrime(N)) {
            N++
        }
        println(N)
    }
}