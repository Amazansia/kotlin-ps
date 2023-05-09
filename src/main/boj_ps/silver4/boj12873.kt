package silver4

import java.util.*

// N % t^3에 해당하는 사람은 없어짐
// 원에 한 명이 남을 때까지 진행...

fun main() = with(System.`in`.bufferedReader()) {

    var N = readLine().toInt()

    var q = LinkedList<Int>()

    // 0 .. N - 1 -> 1 .. N
    for (i in 0 until N) {
        q.add(i + 1)
    }

    var ptr = 0
    var t = 1
    var boj = 0

    while (q.size != 1) {
        // 빠질 사람 정하기

        boj = (ptr + tpow3(t, q.size) - 1) % q.size
        if (boj < 0) boj += q.size
        q.removeAt(boj)
        ptr = boj

        t++
    }

    println(q.poll())
}

fun tpow3(i: Int, mod: Int): Int {
    var ans = i
    ans *= i
    ans %= mod
    ans *= i
    return ans % mod
}
