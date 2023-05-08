package silver2

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var paper = Array(N) { IntArray(N) { 0 } }
    repeat(N) {
        paper[it] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    // 1 - Blue, 0 - White
    var blue = 0
    var white = 0

    fun checkColor(size: Int, x: Int, y: Int) {
        var color = paper[x][y]
        if (size == 1) {
            if (color == 1) blue++ else white++
            return
        }

        for (i in x until x + size) {
            for (j in y until y + size) {
                if (paper[i][j] != color) {
                    checkColor(size / 2, x, y)
                    checkColor(size / 2, x + size / 2, y)
                    checkColor(size / 2, x, y + size / 2)
                    checkColor(size / 2, x + size / 2, y + size / 2)
                    return
                }
            }
        }

        if (color == 1) blue++ else white++
    }

    checkColor(N, 0, 0)
    println("$white\n$blue")
}