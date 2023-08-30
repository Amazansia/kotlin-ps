package gold5
/*
A - B - C - D - E
와 같은 친구관계가 존재하는지 확인하는 프로그램
같은 친구관계가 두 번 이상 주어지지 않음이 보장됨
DFS로 각 노드마다 depth 5 이상인 경우가 존재하는지 돌려보기?
되지않을까?
관계 수 최대 2000
2배로 저장하니까 최대 4000
걍 돌려도 될듯
* */

fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readln().split(" ").map { it.toInt() }

    var relations = Array(N) { mutableListOf<Int>() }

    for (i in 0 until M) {
        var input = readln().split(" ").map { it.toInt() }
        relations[input[0]].add(input[1])
        relations[input[1]].add(input[0])
    }

    var flag: Boolean

    fun dfs(now: Int, visited: BooleanArray, count: Int) {

        visited[now] = true

        if (count == 5) {
            flag = true
            return
        }

        for (next in relations[now]) {
            if (visited[next]) continue
            visited[next] = true
            dfs(next, visited, count + 1)
            visited[next] = false
        }

    }

    for (i in 0 until N) {
        flag = false
        dfs(i, BooleanArray(N), 1)
        if (flag) {
            println(1)
            return@with
        }
    }

    println(0)
}