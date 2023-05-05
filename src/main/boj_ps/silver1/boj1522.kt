package silver1

import kotlin.math.min

/*
a를 모두 연속으로 만든다...
처음과 끝은 서로 인접해 있다
즉, b가 어딘가에 모여 있어야 한다
1. 제일 긴 b의 연속 문자열을 찾는다(양쪽으로 쪼개진 문자열 포함)
2. 거기다가 b를 붙인다

제일 긴 b의 연속 문자열이 1이라면..?

startB~endB까지 중간에 낀 a의 개수를 세서 반띵해서 리턴하면 정답? 말도안되는시도였다ㅋㅋ
abababaabbbbbabababbb -> a 7개
abbbabaabbbbbabababba
abbbbbaabbbbbabababaa
abbbbbbabbbbbababaaaa
abbbbbbbbbbbbabaaaaaa
abbbbbbbbbbbbabaaaaaa
abbbbbbbbbbbbbaaaaaaa
* */
fun main() = with(System.`in`.bufferedReader()) {
    var answer = Int.MAX_VALUE
    var str = StringBuilder(readLine().trim())
    var bCount = str.count { it == 'b' } // sliding window size

    for (i in str.indices) {
        var end = i + bCount
        var idx = i
        var count = 0
        if (end < str.length) {
            // i until end
            while (idx < end) {
                if (str[idx] == 'a') count++
                idx++
            }
        } else {
            // i until str.length
            while (idx < str.length) {
                if (str[idx] == 'a') count++
                idx++
            }
            // 0 until end % str.length
            idx = 0
            while (idx < end % str.length) {
                if (str[idx] == 'a') count++
                idx++
            }
        }
        answer = min(answer, count)
    }

    println(answer)
}