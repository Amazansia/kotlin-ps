package gold5

import kotlin.math.max
import kotlin.math.min

/*
양의 정수 K
조건1. 어떤 문자를 정확히 K개 포함하는 가장 짧은 연속 문자열의 길이를 구한다
조건2. 어떤 문자를 정확히 K개 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.
문자열의 길이는 최대 10000, 게임 횟수 100
1 000 000 0
둘다 투포인터 한번으로 가능할듯


 */

fun main() = with(System.`in`.bufferedReader()) {

    fun printGameResult() {
        var W = readLine()
        var K = readLine().toInt()

        var countAlphabetArr: IntArray

        var firstAnswer = 10001
        var secondAnswer = -1
        // 100 000 000
        // 정확히 K개를 포함하는 가장 짧은 문자열이 존재할 때, 동일한 문자로 시작하는 가장 긴 문자열이 존재할 수 없다.
        for (l in W.indices) {
            countAlphabetArr = IntArray(26) { 0 }
            countAlphabetArr[W[l] - 'a']++
            for (r in l until W.length) {
                if (l != r) {
                    countAlphabetArr[W[r] - 'a']++
                }
                if (W[l] == W[r] && countAlphabetArr[W[l] - 'a'] == K) {
                    firstAnswer = min(firstAnswer, r - l + 1)
                    secondAnswer = max(secondAnswer, r - l + 1)
                    break
                }
            }
        }

        if (firstAnswer != 10001) {
            println("$firstAnswer $secondAnswer")
        } else
            println(-1)
    }

    var T = readLine().toInt()
    repeat(T) {
        printGameResult()
    }

}


