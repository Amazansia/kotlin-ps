package bronzes

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

    for (i in 0 until N) {
        var (origin, strfry) = readLine().split(" ")
        var chararr_origin = IntArray(26)
        var chararr_strfry = IntArray(26)
        var answer = "Possible"

        if (origin.length != strfry.length) {
            println("Impossible")
            continue
        }

        for (j in origin.indices) {
            chararr_origin[origin[j].code - 'a'.code]++
            chararr_strfry[strfry[j].code - 'a'.code]++
        }

        for (j in 0 until 26) {
            if (chararr_strfry[j] != chararr_origin[j]) {
                answer = "Impossible"
                break
            }
        }
        println(answer)
    }
}
