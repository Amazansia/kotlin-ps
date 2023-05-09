package bronzes

fun main() =
    with(System.`in`.bufferedReader()) {
        val X = readLine().toInt()
        var sum = 0
        for (i in 1..readLine().toInt()) {
            val rline = readLine().split(" ")
            sum += rline[0].toInt() * rline[1].toInt()
        }
        println(if (sum == X) "Yes" else "No")
        println()
    }
