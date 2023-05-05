package silver1

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var cp = compareBy<Pair<Int, Int>> { it.second }.thenBy { it.first }
    var arr = Array(N) { Pair(0, 0) }

    for (i in 0 until N) {
        var (s, e) = readLine().split(" ").map { it.toInt() }
        arr[i] = Pair(s, e)
    }

    arr.sortWith(cp)

    var sum = 1
    var now = arr[0].second

    for (i in 1 until N) {
        if (arr[i].first >= now) {
            sum++
            now = arr[i].second
        }
    }
    print(sum)
}