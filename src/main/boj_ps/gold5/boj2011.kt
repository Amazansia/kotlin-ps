package gold5

import kotlin.math.max

/*
10 20외 0이 나오면 리턴
* */

fun main() = with(System.`in`.bufferedReader()) {
    var password = readLine()

    // answer[i] = password의 i번째 자리까지 고려했을 때 가질 수 있는 가장 큰 경우의 수
    var answer = IntArray(password.length)

    if (password.isNotEmpty() && password[0] != '0') {
        answer[0] = 1
    } else {
        println(0)
        return@with
    }

    if (password.length >= 2 && password[1].isDigit()) {
        answer[1] = 1
        var num = password[0].digitToInt() * 10 + password[1].digitToInt()
        if (num in 10..26) {
            answer[1] = 2
        } else if (num > 26 && num % 10 == 0) {
            println(0)
            return@with
        }
    } else {
        println(0)
        return@with
    }

    var idx = 2

    while (idx < password.length) {
        var countweird = 0

        if (idx == 0 && password[idx] != '0') {
            answer[idx] = 1
            idx++
            continue
        }

        // 1자리 숫자 고려
        if (idx >= 1 && password[idx] != '0') {
            answer[idx] = max(answer[idx - 1], answer[idx] + answer[idx - 1])
            answer[idx] %= 1000000
        } else countweird++

        // 2자리 숫자 고려
        var num = password[idx - 1].digitToInt() * 10 + password[idx].digitToInt()
        if (idx >= 1 && num in 10..26) {
            answer[idx] = max(answer[idx - 1], answer[idx] + answer[idx - 2])
            answer[idx] %= 1000000
            if (num == 10 || num == 20) {
                answer[idx] = answer[idx - 2]
            }
        } else if (num > 26 && num % 10 == 0) {
            println(0)
            return@with
        } else countweird++

        if (countweird == 2) {
            println(0)
            return@with
        }

        idx++
    }

    println(answer.last())
}