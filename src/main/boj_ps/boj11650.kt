fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var arr = Array(N) { Pair(0, 0) }
    for (i in 0 until N) {
        var (f, s) = readLine().split(" ").map { it.toInt() }
        arr[i] = Pair(f, s)
    }

    arr.sortWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
    var sb = StringBuilder()
    for (i in arr.indices) {
        sb.append("${arr[i].first} ${arr[i].second}\n")
    }
    print(sb)
}