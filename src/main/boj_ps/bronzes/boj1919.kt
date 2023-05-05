package bronzes

fun main() = with(System.`in`.bufferedReader()) {

    var first = readLine()
    var second = readLine()
    var chararr_first = IntArray(26)
    var chararr_second = IntArray(26)

    var sum = 0

    for (j in first.indices) {
        chararr_first[first[j].code - 'a'.code]++
    }
    for (j in second.indices) {
        chararr_second[second[j].code - 'a'.code]++
    }

    for (j in 0 until 26) {
        if (chararr_second[j] != chararr_first[j]) {
            sum += kotlin.math.abs(chararr_second[j] - chararr_first[j])
        }
    }
    println(sum)
}