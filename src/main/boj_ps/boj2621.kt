import java.lang.Integer.max

fun main() = with(System.`in`.bufferedReader()) {
    // 4장의 숫자가 같을 때 그 숫자 + 800
    // 3장의 숫자가 같고 나머지 2장의 숫자가 같을 때 3장의 같은 숫자 * 10 + 2장이 같은 숫자 + 700
    // 5장의 숫자가 연속적일 때 가장 높은 숫자 + 500
    // 3장의 숫자가 같을 때 + 400
    // 2장의 숫자가 같고 다른 2장의 숫자가 같을 때 큰 숫자에 * 10 + 작은 숫자 + 300
    // 2장의 숫자가 같을 때 같은 숫자에 + 200
    // 어떤 경우에도 해당하지 않을 때 가장 큰 숫자에 + 100

    // 색깔 확인 함수
    // 연속되는 숫자를 가진 카드의 최대 개수 확인 함수

    var cards_color = CharArray(5)
    var cards_num = IntArray(5)
    var answer = 0

    for (i in 0 until 5) {
        var rline = readLine().split(" ")
        cards_color[i] = rline[0][0]
        cards_num[i] = rline[1][0].code - '0'.code
    }

    cards_num.sort()

    // 1, 4
    if (checkColors(cards_color)) {
        if (issequential(cards_num)) {
            answer = max(answer, cards_num.last() + 900)
        }
        answer = max(answer, cards_num.last() + 600)
        print(answer)
        return

    }

    // 2
    if (maxsamenum(cards_num) == 4) {
        print(cards_num[3] + 800)
        return
    }

    if (maxsamenum(cards_num) == 3) {
        // 3
        if (minsamenum(cards_num) == 2) {
            var card2 =
                if (cards_num[2] != cards_num.first()) cards_num.first() else cards_num.last()
            answer = max(answer, card2 + 700 + cards_num[2] * 10)
        }
        // 6
        else {
            answer = max(answer, cards_num[2] + 400)
        }
    }

    // 5
    if (issequential(cards_num)) {
        answer = max(answer, cards_num.last() + 500)
    }

    // 7
    if (maxsamenum(cards_num) == 2) {
        var arr = ArrayList<Int>()
        for (i in cards_num.indices) {
            if (cards_num.count { it == cards_num[i] } == 2 && !arr.contains(cards_num[i])) {
                arr.add(cards_num[i])
            }
        }
        arr.sort()
        if (arr.size == 2) answer = max(answer, arr[0] + 300 + arr[1] * 10)
        // 8
        else answer = max(answer, arr[0] + 200)
    }

    // 9
    answer = max(answer, cards_num.last() + 100)

    print(answer)
    return@with
}

fun issequential(cards: IntArray): Boolean {
    for (i in 0 until 4) {
        if (cards[i] + 1 != cards[i + 1]) return false
    }
    return true
}

fun minsamenum(cards: IntArray): Int {
    for (i in cards.indices) {
        if (cards.count { it == cards[i] } == 2) return 2
    }
    return 1
}

private fun maxsamenum(cards: IntArray): Int {
    var res = 0
    for (i in cards.indices) {
        res = max(res, cards.count { it == cards[i] })
    }
    return res
}

// 다섯 카드의 색이 모두 같은지 체크하는 함수
fun checkColors(cards: CharArray): Boolean {
    var temp = cards[0]
    cards.forEach {
        if (it != temp) return false
    }
    return true
}


