package silver5

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var arr = BooleanArray(21)
    var sb = StringBuilder("")
    repeat(N) {
        var str = readLine().split(" ")
        when (str[0]) {
            "add" -> arr[str[1].toInt()] = true
            "remove" -> arr[str[1].toInt()] = false
            "check" -> sb.appendLine(if (arr[str[1].toInt()]) 1 else 0)
            "toggle" -> arr[str[1].toInt()] = !arr[str[1].toInt()]
            "all" -> arr.fill(true)
            "empty" -> arr.fill(false)
        }
    }
    print(sb)
}