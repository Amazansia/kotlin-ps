import kotlin.math.max

//package kotlin.이모티콘할인행사
//
/*
1. 이모티콘 플러스 가입자 늘리기
2. 이모티콘 판매액 늘리기
n명에게 m개 이모티콘을 할인판매
각 user는 자신만의 기준이 있다: 할인 기준이 자신의 기준을 넘으면 이모티콘을 구매한다
이모티콘 구매 합산액이 상한액을 넘으면 이모티콘 플러스를 가입한다
user 100
m 7
엥?
각 이모티콘의 할인 비율을 고정
user를 돌면서 구매 합산액 계산 및 이모티콘 플러스 가입자 수 계산
max값을 answer에 저장
*
* */

class Solution {
    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        var answer: IntArray = intArrayOf(0, 0)

        val per = intArrayOf(40, 30, 20, 10)

        fun sol(current: Int, emoticonPrice: IntArray) {
            // 여기서 탐색
            // emoticonPrice에 할인율이 저장되어 있는 상태
            if (current == emoticons.size) {
                var plusUser = 0
                var priceSum = 0

                for (user in users) {
                    var sum = 0
                    for (i in emoticonPrice.indices) {
                        if (emoticonPrice[i] >= user[0]) {
                            sum += emoticons[i] * (100 - per[i]) / 100

                        }
                    }
                    if (sum >= user[1]) plusUser++
                    else priceSum += sum
                }
                if (answer[0] < plusUser) {
                    answer[0] = plusUser
                    answer[1] = priceSum
                } else if (answer[0] == plusUser) {
                    answer[1] = max(answer[1], priceSum)
                }
                return
            }

            for (i in per) {
                emoticonPrice[current] = i
                sol(current + 1, emoticonPrice)
            }
        }

        sol(0, IntArray(emoticons.size) { 0 })

        return answer
    }
}