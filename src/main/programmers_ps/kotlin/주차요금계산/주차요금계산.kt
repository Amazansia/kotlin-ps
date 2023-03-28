package kotlin.주차요금계산

import kotlin.math.ceil

/*
차량별 주차 요금 계산
누적 주차시간을 계산
1. 기본시간 이하 -> 기본요금
2. 기본시간 초과 -> 기본요금 + 단위시간마다 단위 요금 청구. 단위시간으로 나누어 떨어지지 않으면 올림
레코드: ~1000까지
시각 차량번호 내역
오름차순 정렬
* */

class Solution {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        var times = mutableListOf<Int>()

        var carMap = records.groupBy { it.split(" ")[1].toInt() }.toSortedMap()

        for (car in carMap) {
            var record = car.value!!
            var sum = if (record.size % 2 != 0) 23 * 60 + 59 else 0
            for (r in record) {
                var rTime = r.split(" ")[0].split(":")
                var time = rTime[0].toInt() * 60 + rTime[1].toInt()
                when (r.split(" ")[2]) {
                    "IN" -> sum -= time
                    else -> sum += time
                }
            }
            times.add(sum)
        }

        var answer = IntArray(times.size)

        for (i in times.indices) {
            if (times[i] <= fees[0]) {
                answer[i] = fees[1]
            } else {
                var plusTime = times[i] - fees[0]
                answer[i] = (fees[1] + ceil(plusTime / fees[2].toDouble()) * fees[3]).toInt()
            }
        }

        return answer
    }
}