package gold2

import java.util.*

/*
무게 M, 가격 V
가방 K개에 최대 무게 C를 담을 수 있음
가방에는 최대 한 개의 보석만 넣을 수 있음?
훔칠 수 있는 보석의 최대 가격의 합
그럼 M > C라면 아예 훔칠 수 없다
k 30만, 가격 100만
맥스로 들어오면 300000 * 10000000...int범위 넘어간다
answer은 long으로 설정
음...
가방무게가 내림차순으로 정렬되어 있다고 가정했을 때
무겁고 가치가 높은...
아니지
보석을 일단 가격순 내림차순 정렬하고 무조건 담는게 이득임
비싼것부터 담는게 무조건 이득
해당 보석을 넣을 수 있느냐 없느냐는...
일단담는데 담을때
가방을 무게순으로 오름차순 정렬해놓고 담는게 불가능할때까지 뒤로 미룸
한계선을 찾으면 해당 가방에 넣어놓고 그가방은 리스트에서 빼버림
이짓을 가방이다찰때까지 || 더담을보석이 없을때까지 반복한다
그럼 미루는과정은 logn으로 떨어져야하는데
이진트리형 정렬?
큐쓰면 될듯
이 적절한 자리를...찾는게 문제다
큐쓰면되는거맞어?????
while
보석을 가벼운 것부터 & 가격이 비싼 것부터 일단 담는다
가방 무게 기준으로 정렬
가벼운 것부터~A라는 가방의 마지노선까지
가격이 비싼 보석
우선순위 큐: 가격이 비싼 것부터 먼저 나오게
* */

fun main() = with(System.`in`.bufferedReader()) {
    var (N, K) = readLine().split(" ").map { it.toInt() }

    // 보석 정보 저장
    var jewels = Array(N) { Pair(0, 0) }
    for (i in 0 until N) {
        jewels[i] = readLine().split(" ").map { it.toInt() }.let { it[0] to it[1] }
    }

    jewels.sortWith(compareBy<Pair<Int, Int>> { it.first }.thenByDescending { it.second })

    // 가방 정보 저장
    var bags = IntArray(K)
    for (i in 0 until K) {
        bags[i] = readLine().toInt()
    }

    bags.sort()

    var pq = PriorityQueue<Int>(Comparator.reverseOrder())

    var answer = 0L
    var idx = 0
    for (i in 0 until K) {
        while (idx < N && jewels[idx].first <= bags[i]) {
            pq.offer(jewels[idx++].second)
        }
        if (!pq.isEmpty()) {
            answer += pq.poll().toLong()
        }
    }

    println(answer)
}