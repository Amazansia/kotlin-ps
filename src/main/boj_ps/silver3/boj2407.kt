package silver3

import java.math.BigInteger

/*
100 6

* */
fun main() = with(System.`in`.bufferedReader()) {
    var (n, m) = readLine().trim().split(" ").map { it.toInt() }

    var answer = BigInteger("1")
    for (i in n - m + 1..n) {
        answer = answer.multiply(BigInteger("$i"))
    }
    for (i in 1..m) {
        answer = answer.divide(BigInteger("$i"))
    }
    print(answer)
}