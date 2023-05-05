package bronzes

fun main() = with(System.`in`.bufferedReader()) {
    var str = readLine()
    while (str != "0") {
        var answer = "yes"
        var len = str.length
        for (i in 0 until len / 2) {
            if (str[i] != str[len - i - 1]) {
                answer = "no"
                break
            }
        }
        println(answer)
        str = readLine()
    }
}