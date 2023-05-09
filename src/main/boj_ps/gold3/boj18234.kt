package gold3

import java.util.*

/*
당근은 맛있다
텃밭에 N개의 당근을 T일 동안 재배할 예정
당근은 Wi의 맛이고, Pi만큼 맛을 증가시켜주는 영양제가 T개씩 준비되어 있다
당근이 자리에 없으면 당근을 심고 아니면 영양제를 하나 준다
토끼는 당근을 하나씩 먹거나 안 먹는다
당근 맛의 합이 최댓값이 되었으면 좋겠다
T일 동안 토끼가 먹을 수 있는 맛의 최댓값을 구해보자
N은 20만, T는 1억...
1. 시뮬?
T번 돌아가는 for문...
N개의 배열을 매번 업데이트하면서?
N*T로 터질 듯
2. 공식?
i일차 당근의 맛: w + (i-1) * p
토끼가 뽑아먹으면 다시 0으로 설정...
뽑아먹는 순서는 p가 작은 순?
우선순위 큐?
1 100
1 100000 p > w
이런 입력이 들어왔을 때...계속 먹지 않다가 마지막에 한 번 먹는 것이 가장 큼
그럼
1 100
100 100 p == w
이런 입력에서는 매일매일 먹어도 상관없지만 마지막에 한 번 먹는 것과 값이 똑같음
1 100
100 99 p < w
이런 입력에서는 매일매일 먹는 게 이득
우선순위 큐에 idx, w, p add
그럼
2 100
100 99
1 100000
100 100
second 내림차순, third 오름차순
100 99
100 100
1 100000
1. p > w일 때 queue에 넣기
2. p <= w 일 때 가장 큰 것만 매일매일 뽑아먹기
* */

fun main() = with(System.`in`.bufferedReader()) {
    var (N, T) = readLine().trim().split(" ").map { it.toInt() }

    // idx, w, p: 1. w 내림차순
    var pq = PriorityQueue(Comparator<Pair<Int, Int>> { a, b ->
        when {
            a.second != b.second -> a.second - b.second
            else -> a.first - b.first
        }
    })

    repeat(N) {
        var str = readLine().split(" ").map { it.toInt() }.toIntArray()
        pq.add(Pair(str[0], str[1]))
    }

    // i일차 당근의 맛: w + (i-1) * p
    var answer: Long = 0

    // 기다린 당근 먹기
    var remainDays = T - N
    while (pq.isNotEmpty()) {
        var i = pq.poll()
        answer += i.first + remainDays * i.second.toLong()
        remainDays++
    }

    print(answer)
}