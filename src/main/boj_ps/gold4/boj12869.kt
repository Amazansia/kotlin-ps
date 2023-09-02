package gold4

import java.util.*

/*
첫딜 9 두번째 3 세번째 1
체력 0 이하일 때 scv 파괴
N == 1이면 9로 나누고 나머지 존재할 경우 +1한 값이 정답
N == 2면 pq로 내림차순 정렬하고 9 3씩 딜넣어보면서 최솟값 구하기
N == 3이면...
내림차순 정렬한다고 무조건 모든 조건에 맞게 동작하는가?
10 9 1은?
1.
9 6 0
0 3 0
0 0 0
2.
1 9 1
0 0 0
내림차순 정렬로는 안된다
dfs 써야함
순서조합 6개 다 돌려봐야됨
즉 시복 6^N


* */

fun main() = with(System.`in`.bufferedReader()) {
    var N = readln().toInt()
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()


    when (N) {
        1 -> {
            println(arr[0] / 9 + if (arr[0] % 9 == 0) 0 else 1)
        }

        2 -> {
            var pq = PriorityQueue<Int>(Comparator.reverseOrder())
            pq.add(arr[0])
            pq.add(arr[1])

            var answer = 0

            while (pq.isNotEmpty()) {

                answer++

                var first = pq.poll()
                var second = 0
                if (pq.isNotEmpty()) second = pq.poll()
                if (first - 9 > 0) pq.add(first - 9)
                if (second - 3 > 0) pq.add(second - 3)
            }

            println(answer)
        }

        else -> {
            var answer = 0


            // 6개 경우의 수 다 체크하면서 돌려야됨
            dfs()
        }
    }

}