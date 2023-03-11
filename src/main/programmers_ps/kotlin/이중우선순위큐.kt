package kotlin

import java.util.*

/*
I 숫자 -> 삽입
D 1 -> 최댓값 삭제
D -1 -> 최솟값 삭제
큐 두개?

operations 길이는 최대 1백만...nlogn도 터질거같은데
deque: 오름차순 12345
* */

//fun main() = with(System.`in`.bufferedReader()) {
class Solution {
    fun solution(operations: Array<String>): IntArray {

        var answer = intArrayOf(0, 0)
        var sm: SortedMap<Int, Int> = TreeMap()
        for (i in operations) {
            var str = i.split(" ")
            // 삽입
            if (str[0] == "I") {
                var num = str[1].toInt()
                sm[num] = sm.getOrDefault(num, 0) + 1
            }
            // 삭제
            else if (sm.isNotEmpty()) {
                var key = if (str[1] == "1") sm.lastKey() else sm.firstKey()

                if (sm.getOrDefault(key, 0) > 0) {
                    sm[key] = sm.getOrDefault(key, 0) - 1
//                    println(sm[key])
                    if (sm.getOrDefault(key, -1) == 0) {
//                        print("?")
                        sm.remove(key)
                    }
                }
            }
        }

//        sm.forEach { println("${it.key}, ${it.value}") }

        if (sm.isNotEmpty()) {
            answer[1] = sm.firstKey()
            answer[0] = sm.lastKey()
        }

        return answer
    }
}
//}

