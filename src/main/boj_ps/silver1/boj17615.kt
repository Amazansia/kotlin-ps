package silver1
/*
??
N 최대 50만
목표는 볼을 이동시켜서 같은 색끼리 모을 수 있는 최소 이동횟수를 찾는 것.
법칙 1. 바로 옆에 다른 색깔의 볼이 있으면 모두 뛰어넘어 옮길 수 있다.
법칙 2. 옮길 수 있는 볼의 색깔은 한가지이다. 빨간색 or 파란색
빨간색 파란색 / 왼쪽 오른쪽 한번씩 옮겨보기

* */

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var str = readLine()
    var first = 0
    var last = 0

    var red = 0
    var blue = 0
    var idx = 0

    while (idx < N && str[idx] == str.first()) {
        first++
        idx++
    }

    if (idx == N - 1) {
        println(0)
        return@with
    }
    idx = N - 1

    while (idx >= 0 && str[idx] == str.last()) {
        last++
        idx--
    }

}