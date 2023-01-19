fun main() = with(System.`in`.bufferedReader()) {
    var T = readLine().toInt()
    var arr = Array(15) { IntArray(15) }

    for (i in 0 until 15) {
        arr[0][i] = i
    }

    for (i in 1..14) {
        for (j in 1..14) {
            arr[i][j] = arr[i - 1][j] + arr[i][j - 1]
        }
    }

    repeat(T) {
        var k = readLine().toInt()
        var n = readLine().toInt()

        println(arr[k][n])
    }
}