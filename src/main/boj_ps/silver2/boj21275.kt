package silver2

fun main() = with(System.`in`.bufferedReader()) {
    /*
    X: 10진법 -> 2^63미만, Long 사용해야 함, 계산시 overflow 조심
    X를 A진법, B진법으로 나타낸 수가 주어진다.
    최대 진수는 0 ~ z까지, 36진수이다.
    최소 2진법부터 시작하여 36진법까지 가능.
     */

    val (X_A, X_B) = readLine().split(" ")
    var answer = "Impossible"
    var cnt = 0

    // i진법으로 표기된 X_A의 값을 10진법으로 변환하여 temp에 저장
    for (i in 2..36) {
        for (j in 2..36) {
            if (i == j)
                continue
            try {
                var temp = X_A.toLong(i)
                if (temp == X_B.toLong(j)) {
                    cnt++
                    answer = "$temp $i $j"
                }
            } catch (_: java.lang.NumberFormatException) {
            }
        }
    }

    if (cnt > 1) print("Multiple") else print(answer)
}
