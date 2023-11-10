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

        var left = 0
        var right = 0
        var countAlphabetArr = IntArray(26) { 0 }
        countAlphabetArr[W[right] - 'a']++


        var firstAnswer = 10001

        while (right < W.length && left <= right) {
            if (countAlphabetArr[W[right] - 'a'] > K) {
                countAlphabetArr[W[left] - 'a']--
                left++
            } else {
                if (countAlphabetArr[W[right] - 'a'] == K) {
                    var l = left
                    while (W[l] != W[right]) {
                        l++
                    }
                    firstAnswer = min(firstAnswer, right - l + 1)
                }
                right++
                if (right == W.length) break
                countAlphabetArr[W[right] - 'a']++
            }
        }

        left = 0
        right = 0
        countAlphabetArr = IntArray(26) { 0 }
        countAlphabetArr[W[right] - 'a']++

        var secondAnswer = -1

        while (right < W.length && left <= right) {
//            println("$left, $right")

            if (countAlphabetArr[W[right] - 'a'] > K) {
                countAlphabetArr[W[left] - 'a']--
                if (countAlphabetArr[W[right] - 'a'] == K && W[right] == W[left]) {
                    secondAnswer = max(secondAnswer, right - left)
                }
                left++

            } else {
                right++
                if (right == W.length) break
                countAlphabetArr[W[right] - 'a']++
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


