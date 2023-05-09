package silver1

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()

    var arr = Array(N) { IntArray(N) { 0 } }
    var INF = 987654321
    for (i in 0 until N) {
        arr[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    for (k in 0 until N) {
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (arr[i][k] == 1 && arr[k][j] == 1) {
                    arr[i][j] = 1
                }
            }
        }
    }

    var sb = StringBuilder()
    for (i in 0 until N) {
        for (j in 0 until N) {
            sb.append("${arr[i][j]} ")
        }
        sb.append("\n")
    }
    print(sb)


}