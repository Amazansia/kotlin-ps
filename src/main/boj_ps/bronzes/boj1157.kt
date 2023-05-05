package bronzes

fun main() = with(System.`in`.bufferedReader()) {
    var str = readLine().lowercase()
    var arr = IntArray(26)
    for (i in str.indices) {
        arr[str[i].code - 'a'.code]++
    }
    var answer = ""
    var maxvalue = arr.maxOrNull() ?: 0
    for (i in arr.indices) {
        if (arr[i] == maxvalue && answer.isEmpty()) answer = (i + 'a'.code).toChar().uppercase()
        else if (arr[i] == maxvalue && answer.isNotEmpty()) answer = "?"
    }
    println(answer)
}