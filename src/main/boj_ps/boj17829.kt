fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()

    var matrix = Array(N) { IntArray(N) }

    for (i in 0 until N) {
        matrix[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    fun solution(i: Int, j: Int, size: Int): Int {
        var arr = IntArray(4)
        if (size == 2) {
            var idx = 0
            for (a in i until i + 2) {
                for (b in j until j + 2) {
                    arr[idx++] = matrix[a][b]
                }
            }

        } else {
            var newSize = size / 2
            arr[0] = solution(i, j, newSize)
            arr[1] = solution(i, j + newSize, newSize)
            arr[2] = solution(i + newSize, j, newSize)
            arr[3] = solution(i + newSize, j + newSize, newSize)
        }

        arr.sort()
        return arr[2]
    }

    println(solution(0, 0, N))
}
