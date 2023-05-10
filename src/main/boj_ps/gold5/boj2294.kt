package gold5

import kotlin.math.min

/*
dfs?
depth 100인디... dfs 안될듯
백트래킹?
각각의 동전은 계속 사용할 수 있다...
dp?
100 00 00 00...4승까지 가능할듯
answer 배열 intmax로 초기화
arr 다 돌면서 배수에 따른 최소 동전 갯수 저장
작은 수부터 최소 동전의 개수 저장
bottomup으로 플로이드 워셜 비슷하게...
answer[i] = i를 완성하는데 필요한 동전의 최소 개수
* */
fun main() = with(System.`in`.bufferedReader()) {
    var (n, k) = readLine().trim().split(" ").map { it.toInt() }
    var answer = IntArray(k + 1) { Int.MAX_VALUE }
    var arr = IntArray(n)
    repeat(n) {
        arr[it] = readLine().trim().toInt()
    }

    // k 이상인 원소 & 중복된 원소 걸러내고 정렬
    arr = arr.filter { it <= k }.distinct().toIntArray().sortedArray()

    for (i in arr) {
        var mul = 1
        while (mul * i <= k) {
            answer[mul * i] = min(mul, answer[mul * i])
            mul++
        }
    }

    for (i in 1..k) {
        for (j in 1..k) {
            if (i + j <= k) {
                var tempLong = answer[i].toLong() + answer[j]
                if (tempLong > k) continue
                answer[i + j] = min(answer[i + j], answer[i] + answer[j])
            }
        }
    }

//    answer.forEach { println(it) }

    println(if (answer[k] == Int.MAX_VALUE) -1 else answer[k])
}