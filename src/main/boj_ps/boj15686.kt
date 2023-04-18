/*
0은 빈칸, 1은 집, 2는 치킨집
치킨거리:
    집~가장 가까운 치킨집 사이 거리
    집을 기준으로 정해짐
도시의 치킨거리:
    모든 집의 치킨 거리의 합
M개의 치킨집만 남기고 나머지는 폐업시킨다고 했을 때, 도시의 치킨거리가 가장 적게 될지 구하여라
N*N의 도시 -> N ~50
M ~13
최대 M개를 고르고 나머지는 폐업....
dfs로 조합 구하기 + 해당 조합의 치킨거리 구하는 함수 or 그리디
집의 위치, 치킨집의 위치를 따로 저장해야 할 듯
치킨거리 구하는 함수의 시간복잡도: 집 최대 2500-13 -> K라고 하면 M*K만큼 돌아감
List<Pair>로 저장해야 할 듯
그리디로 가능?
치킨거리가 가장 큰 치킨집을 먼저 폐업시킨다?
xxx 안될듯

* */
import kotlin.math.abs
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {

    var answer = Int.MAX_VALUE
    var (N, M) = readLine().trim().split(" ").map { it.toInt() }

    var house = mutableListOf<Pair<Int, Int>>()
    var cc = mutableListOf<Pair<Int, Int>>()

    for (i in 0 until N) {
        var str = readLine().trim().split(" ").map { it.toInt() }
        for (j in str.indices) {
            when (str[j]) {
                1 -> house.add(i to j)
                2 -> cc.add(i to j)
            }
        }
    }

    var visited = BooleanArray(cc.size) { false }

    // visited 조합의 치킨거리를 계산하여 리턴하는 함수
    fun calculateDistance(visited: BooleanArray): Int {
        var ret = 0
        var remainCC = cc.filterIndexed { i, _ -> visited[i] }

        for (h in house) {
            var sum = Int.MAX_VALUE
            for (c in remainCC) {
                sum = min(
                    sum,
                    abs(c.first - h.first) + abs(c.second - h.second)
                )
            }
            ret += sum
        }
//        println(visited.joinToString(" "))
//        println(ret)
        return ret
    }

    fun dfs(now: Int, count: Int, visited: BooleanArray) {
        visited[now] = true

        if (count == M) {
//            println("dfs Return")
            answer = min(calculateDistance(visited), answer)
            return
        }

        for (i in 0 until cc.size) {
            if (visited[i] || i < now) continue
            dfs(i, count + 1, visited)
            visited[i] = false
        }
    }


    for (i in 0 until cc.size) {
        visited.fill(false)
        dfs(i, 1, visited)
    }

    println(answer)
}