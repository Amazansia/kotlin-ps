package coding_test

import java.util.*
import kotlin.math.max

/*
게임 n개 할인
각 이벤트 기간 내 딱 하루만 할인
하루에 하나 이상의 게임을 구매해야 한다
n은 1000...
1. 게임은 할인하는 날에 사는 게 이득이다.
2. 게임을 할인하는 날에 모두 구입한다고 생각해 보자.
     -> 여러 게임이 한 날에 할인하면?
마지막 날짜는 항상 고정인가? X
1 1 1 1 1 100.... -> 이때 1일차 게임이 할인할 때 모두 구입하는 것이
100일까지 기다리는 것보다 이득일 수 있음
dp배열이나 for문으로 endday == 0부터 N-1까지 모든 날을 훑어야 함 -> 이건 무조건
for문 내부에서는?
일단 할인하는 게임을 모두 구입하고
할인액이 낮은 순으로 남은 날짜를 채워야... -> 이건 모든 날짜에서 검색해야 함
1. games 배열에서 할인액을 미리 계산해 두고 이것을 pq에 넣어둠. 오름차순 정렬: 할인받지 않아도 손해가 적은 순 정렬
2. k번째 날에 종료된다고 가정하고 for문을 돌려 본다.
3. k번째 날짜까지 할인하는 게임은 모두 구매한다고 가정한다.

***
k번째 날에 종료된다고 생각하고
1. k번째 날까지 할인하는 게임은 모두 할인 list에 넣는다 - 할인액이 작은 것부터 정렬
2. k+1날부터 할인하는 게임은 모두 q에 넣는다 - 정렬 상관x
1부터 k까지 순회하며 비는 날이 있다면?
    2번 q의 게임을 먼저 사용하여 채우고, 2번 q가 비면 할인 list에서 pop하여 채운다.
    할인 pq에서 pop되는 게임은 할인받을 수 없는 게임이다.
*/
fun main() = with(System.`in`.bufferedReader()) {
    fun solution(games: Array<IntArray>): Int {
        var answer = 0

        // 날짜순 묶기
        var gamesByDate = games.groupBy { it[1] }

        var days = games.size
        // k번째 날에 종료된다고 생각하면...
        for (k in 0 until days) {
            // 최대 할인액
            var sum = 0
            // 게임을 이미 구매한 날
            var bought = 0
            var list = PriorityQueue<Int>() // 오름차순 정렬

//          1. k번째 날까지 할인하는 게임 중 할인액이 가장 큰 것은 해당 날짜에 고정한다.(즉, 더 고려하지 않는다)
//          2. k번째 날까지 할인하는 게임 중, 날짜가 고정되지 않은 게임 & k+1번째 날부터 할인하는 게임의 "할인액"을 모두 list에 넣는다.
            for (date in gamesByDate) {
                if (date.key <= k) {
                    bought++
                    var valuelist = date.value.sortedBy { it[0] * it[2] / 100 }.toMutableList()
                    sum += valuelist.sumOf { it[0] * it[2] / 100 }
                    valuelist.removeLast()
                    list.addAll(valuelist.map { it[0] * it[2] / 100 }.toList())
                }
                // 정가를 줘야 하는 게임들
                else {
                    list.addAll(Array(date.value.size) { 0 })
                }
            }

            while (k - bought >= 0) {
                sum -= list.poll()
                bought++
            }
            answer = max(answer, sum)
        }

        return games.sumOf { it[0] } - answer
    }

    var tc1 =
        arrayOf(intArrayOf(66000, 0, 50), intArrayOf(16000, 2, 10), intArrayOf(84500, 3, 20), intArrayOf(5500, 2, 75))
    print(solution(tc1))
}