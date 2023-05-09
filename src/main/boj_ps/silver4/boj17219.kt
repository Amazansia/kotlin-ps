package silver4

fun main() = with(System.`in`.bufferedReader()) {
    var (N, M) = readLine().split(" ").map { it.toInt() }

    var map = HashMap<String, String>()
    for (i in 0 until N) {
        var str = readLine().split(" ")
        map[str[0]] = str[1]
    }

    var sb = StringBuilder()

    for (i in 0 until M) {
        var str = readLine()
        sb.append("${map[str]}\n")
    }
    print(sb)
}