package gold2

fun main() = with(System.`in`.bufferedReader()) {
    var info = Array(4) { Array(4) { IntArray(2) } }

    for (i in 0 until 4) {
        var str = readln().split(" ").map { it.toInt() }
        for (j in 0 until 4) {
            info[i][j] = intArrayOf(str[j * 2], str[j * 2 + 1])
        }
    }
}