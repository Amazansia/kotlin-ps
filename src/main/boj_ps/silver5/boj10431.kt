package silver5

fun main() = with(System.`in`.bufferedReader()) {
    var P = readLine().toInt()
    var sb = StringBuilder()
    repeat(P) {
        var arr = readLine().split(" ").map { it.toInt() }.drop(1).toIntArray()
        var answer = 0
        var list = mutableListOf<Int>()
        for (i in arr) {
            var size = list.size
            for (l in list.indices) {
                if (i < list[l]) {
                    answer += list.size - l
                    list.add(l, i)
                    break
                }
            }
            if (size == list.size) list.add(i)
        }
        sb.appendLine("${it + 1} $answer")
    }
    print(sb)
}