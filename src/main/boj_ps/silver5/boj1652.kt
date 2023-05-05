package silver5

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var row_count = 0
    var col_count = 0
    var room = Array(N) { "" }
    for (i in 0 until N) {
        room[i] = readLine()
    }

    var j = 0

    for (i in 0 until N) {
        // 가로로 눕는 경우
        while (j < N - 1) {
            if (room[i][j] == '.' && room[i][j + 1] == '.') {
                row_count++
                j += 2
                while (j < N - 1 && room[i][j] == '.') j++
            }
            j++
        }
        j = 0
    }

    for (i in 0 until N) {
        // 세로로 눕는 경우
        while (j < N - 1) {
            if (room[j][i] == '.' && room[j + 1][i] == '.') {
                col_count++
                j += 2
                while (j < N - 1 && room[j][i] == '.') j++
            }
            j++
        }
        j = 0
    }

    println("$row_count $col_count")
}