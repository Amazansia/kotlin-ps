package gold5
/*
두 조각 길이의 합은 구멍의 너비와 정확하게 일치해야 한다...
구멍을 완벽하게 막을 수 있는 두 조각은?
x는 센티미터, l은 나노미터...
*10000000
합을 만들 수 있는 조합...
중에 거리가 제일 큰 것 먼저 출력
없으면 danger
n 백만개들어옴
정렬함때려?
될듯
정렬함하고
s / e 쓰면서
*/

fun main() = with(System.`in`.bufferedReader()) {
    var str = readLine()

    fun solve(str: String) {
        var X = str.toInt() * 10000000
        var N = readLine().toInt()
        var arr = IntArray(N)
        for (i in 0 until N) {
            arr[i] = readLine().toInt()
        }


    }
    if (str.isNullOrEmpty()) {
        solve(str)

        str = readLine()
    }
}