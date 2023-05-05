package silver1

fun main() = with(System.`in`.bufferedReader()) {
    // N보다 크거나 같고, 소수 && 펠린드롬인 가장 작은 수
    val N = readLine()

    var num = N.toInt()
    if (num == 1) {
        println(2)
        return
    }
    while (num > 1) {
        if (ispalindrome(num) && isprime(num)) {
            println(num)
            break
        }
        num++
    }
}

fun isprime(num: Int): Boolean {
    val num_string = num.toString()
    for (i in 2..kotlin.math.sqrt(num.toDouble()).toInt()) {
        if (num % i == 0) {
            return false
        }
    }
    return true
}

// 펠린드롬 판별함수
fun ispalindrome(num: Int): Boolean {
    val num_string = num.toString()
    for (i in 0 until num_string.length / 2) {
        if (num_string[i] != num_string[num_string.length - i - 1]) {
            return false
        }
    }
    return true
}
