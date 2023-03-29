package kotlin_.숫자문자열과영단어

class Solution {
    fun solution(s: String): Int {
        var answer = s

        var word = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

        word.forEachIndexed { i, c ->
            answer = answer.replace(c, i.toString())
        }

        return answer.toInt()
    }
}