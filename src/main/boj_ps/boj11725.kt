import java.util.*

// 그래프 탐색(BFS, DFS)으로 1(루트 노드)까지 도달하는 경로가 매 노드마다 반드시 하나씩 존재한다
// 그러한 경로가 존재할 경우, 자기 자신~루트 노드까지의 경로 순서 중 자기 자신 다음으로 오는 노드가 부모 노드
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

//    var nodes = Array(N + 1) { ArrayList<Int>() }
//    repeat(N + 1) {
//        nodes[it] = ArrayList()
//    }

    var nodes: Array<ArrayList<Int>> = Array(N + 1) { arrayListOf() }

    repeat(N - 1) {
        var (start, end) = readLine().split(" ").map { it.toInt() }
        nodes[start].add(end)
        nodes[end].add(start)
    }
//    nodes.forEach { it.sort() }

    /*
    // 시간초과 뜬 dfs코드
    // BFS로 루트 노드부터 탐색하면서 부모노드 체크해 주는게 시간 더 빠를듯
    fun dfs(start: Int, depth: Int, parent: Int) {
        if (start == 1) {
            println(parent)
            return
        }
        visited[start] = true

        for (i in nodes[start]) {
            if (!visited[i]) {
                dfs(i, depth + 1, if (depth == 0) i else parent)
//                if (depth == 0) {
//                    dfs(i, depth + 1, i)
//                } else {
//                    dfs(i, depth + 1, parent)
//                }
                visited[i] = true
            }
        }
    }

    for (i in 2..N) {
        visited = BooleanArray(N + 1) { false }
        dfs(i, 0, 0)
    }

     */

    // BFS로 루트노드부터 부모노드 체크해 주기

    var visited = BooleanArray(N + 1) { false }
    var parent = IntArray(N + 1) { 0 }
    var q: Queue<Int> = LinkedList()

    visited[1] = true
    q.add(1)

    while (q.isNotEmpty()) {
        var front = q.poll()
        for (i in nodes[front]) {
            if (!visited[i]) {
                parent[i] = front
                q.add(i)
                visited[i] = true
            }
        }
    }

    var bw = System.`out`.bufferedWriter()

    for (i in 2..N) {
        bw.append("${parent[i]}\n")
    }
    bw.flush()
    bw.close()
}