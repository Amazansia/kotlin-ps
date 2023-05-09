package bronzes

import kotlin.math.ceil

/*
W개씩 H행에 걸쳐..
세로로 n칸 or 가로로 m칸 이상 비워야 앉을 수 있다

* */
fun main() = with(System.`in`.bufferedReader()) {
    var (H, W, N, M) = readLine().trim().split(" ").map { it.toInt() }
    var a = ceil(H / (N + 1).toDouble()).toInt()
    var b = ceil(W / (M + 1).toDouble()).toInt()
    println(
        a * b
    )
}