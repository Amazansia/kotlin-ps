package coding_test

import java.util.*
import kotlin.math.min

/*
문제 설명
n은 도네이션 리스트
 */

fun solution(n: Int, c: Int, d: Int, a: Array<String>, b: IntArray): Long {
    var answer = b.sum() * 100
	var temp = ArrayList<Int>()

    var donationList: SortedMap<String, Long> = TreeMap()
    for (i in 0 until n) {
        var temp = donationList.getOrDefault(a[i], 0) + b[i]
        donationList[a[i]] = temp
    }

    // 개인의 도네액들이 오름차순으로 1 2 3 4 5 10
    var list = donationList.values.toList().filterNotNull().sortedDescending()

    var minTax = Long.MAX_VALUE
    var g2Size = list.size
    var g1Tax = 0L

    for (element in list) {
        if (g2Size in list.indices) {
            var g2Tax = d.toLong() * g2Size * g2Size
            g1Tax += c * element

            minTax = min(g1Tax + g2Tax, minTax)
            g2Size--
        }
    }

    minTax = min(c * b.sum().toLong(), minTax)
    minTax = min(list.size * list.size * d.toLong(), minTax)

    return answer - minTax
}

fun main() = with(System.`in`.bufferedReader()) {

    // 정답 260
    // 1 2 3 4 5 100 200 300 400 500
    // g1 -> C * 총합 * 100 -> 100 * 3 * 100 -> 30000
    // g2 -> d * |g2|^2 -> 10 * 4 -> 40
    // 3 * 100 - 40 -> 260
    println(solution(2, 100, 10, arrayOf("A", "B"), intArrayOf(1, 2)))

    // 정답 297
    println(solution(2, 1, 10000, arrayOf("A", "B"), intArrayOf(1, 2)))
    /* 여기까지 확인함 */

    println(solution(2, 1, 10000, arrayOf("A", "A"), intArrayOf(1, 2)))
    println(solution(2, 100, 10, arrayOf("A", "A"), intArrayOf(1, 2)))
    println(solution(3, 0, 10, arrayOf("A", "B", "C"), intArrayOf(1, 2, 3)))
    println(solution(3, 1, 10000, arrayOf("A", "B", "C"), intArrayOf(1, 2, 3)))
    println(solution(5, 10, 10, arrayOf("A", "B", "C", "D", "E"), intArrayOf(1, 2, 3, 4, 1000000)))

}