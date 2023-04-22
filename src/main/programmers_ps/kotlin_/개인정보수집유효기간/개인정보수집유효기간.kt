//package kotlin_.개인정보수집유효기간

class Solution {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        fun calculateDate(str: String): Int {
            var temp = str.split(" ").map { it.toInt() }
            return (temp[0] - 2000) * 12 * 28 + temp[1] * 28 + temp[0]
        }

        var expireTime = HashMap<String, Int>()
        for (term in terms) {
            var t = term.split(" ")
            expireTime[t[0]] = t[1].toInt()
        }

        var answer = mutableListOf<Int>()
        var todayNum = calculateDate(today)

        fun isExpired(date: Int, term: String): Boolean {
            return date + expireTime.getOrDefault(term, 0) * 28 > todayNum
        }




        return answer.toIntArray()
    }
}