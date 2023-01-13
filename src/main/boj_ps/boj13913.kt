import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map { it.toInt() }

    val len = 100001
    var visited = BooleanArray(len) { false }
    var q: Queue<Pair<Int, Int>> = LinkedList()
    var root = Stack<Pair<Int, Int>>()

    visited[N] = true
    q.add(Pair(N, 0))
    var time = 987654321

    while (q.isNotEmpty()) {
        var front = q.poll()
        root.add(front)
        visited[front.first] = true

        // N==K일 때
        if (front.first == K) {
            time = front.second
            break
        }

        // 뒤로 걷기
        if (front.first - 1 >= 0 && !visited[front.first - 1]) {
            q.add(Pair(front.first - 1, front.second + 1))
        }
        // 앞으로 걷기
        if (front.first + 1 < len && !visited[front.first + 1]) {
            q.add(Pair(front.first + 1, front.second + 1))
        }
        if (front.first * 2 < len && !visited[front.first * 2]) {
            q.add(Pair(front.first * 2, front.second + 1))
        }
    }

    var answer = mutableListOf<Int>()

    fun isPossible(front: Int, num: Int): Boolean {
        if (front + 1 == num || front - 1 == num || front * 2 == num)
            return true
        return false
    }

    var K_pair = root.pop()
    var num = K_pair.first
    var now = K_pair.second
    answer.add(num)

    while (root.isNotEmpty()) {
        var front = root.pop()
//        println(front.first)
        if (front.second == now) {
            continue
        }
        // front.first에서 이동했을 때 num 위치로 갈 수 있다면
        if (isPossible(front.first, num)) {
            num = front.first
            now = front.second
            answer.add(num)
        }
    }


    println(time)
    for (i in answer.size - 1 downTo 0) {
        print("${answer[i]} ")
    }

}