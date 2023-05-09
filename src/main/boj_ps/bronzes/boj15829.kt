package bronzes

fun main() = with(System.`in`.bufferedReader()) {
    var L = readLine().toInt()
    var str = readLine()
    var sum: Long = 0
    var pownum: Long = 1
    for (i in str.indices) {
        sum = (sum + ((str[i].code - 'a'.code + 1) * pownum)) % 1234567891
        pownum = (pownum * 31) % 1234567891
    }
    print(sum)
}