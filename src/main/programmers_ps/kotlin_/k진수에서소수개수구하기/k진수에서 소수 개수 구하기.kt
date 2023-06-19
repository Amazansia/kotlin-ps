package kotlin_.k진수에서소수개수구하기

import kotlin.math.sqrt

/*
해당되는 소수가 중복되더라도 그냥 센다
k진수로 바꿨을 때 변환된 수 안에 조건에 맞는 수가 얼마나 있는가...
양의 정수 n이 주어졌을 때 이 수를 k진수로 바꾼다
* */

class Solution {
    fun solution(n: Int, k: Int): Int {
        var answer: Int = 0
        var str = n.toString(k)
        var list = str.split("0")

        fun isPrime(num: Long): Boolean {
            if (num == 1L) return false
            for (i in 2..sqrt(num.toDouble()).toLong()) {
                if (num % i == 0L) {
                    return false
                }
            }
            return true
        }

        list = list.filter { it.isNotEmpty() }

        for (i in list) {
            if (isPrime(i.toLong(10))) {
                answer++
            }
        }

        return answer
    }
}