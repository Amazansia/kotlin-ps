package kotlin.마법의엘리베이터
/*
100
-> 1
999
1000 - 1
-> 2
1
989
1000 - 10 - 1

* */

class Solution {
    fun solution(storey: Int): Int {

        var answer = 0
        var s = storey

        while (s != 0) {
            var num = s % 10
            // 나머지 6 || 나머지 5 && 다음 나머지도 5
            if (num >= 6 || num == 5 && (s / 10) % 10 >= 5) {
                s += 10 - num
                answer += 10 - num
            } else {
                answer += num
            }
            s /= 10
        }

        return answer
    }
}