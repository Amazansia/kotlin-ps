package gold5

import kotlin.math.floor
import kotlin.math.sqrt

/*
1 2 3 4 5...
y 1
2
1
2^31 -> O(N) LogN? -> 어떤 공식이 있을 거라고 -> 수학
이전 작동시기에 k를 이동했다면 k-1, k, k+1만을 이동할 수 있다
y지점에 도착하기 직전의 이동거리는 반드시 1광년이어야 함
즉, 직전에는 y-1 or y+1에 도착해야 한다
그럼 0 10은?
1: 1
2: 2
3: 3
4: 2 -> 9 or 11에 도착해야 함
5: 1 -> 10 도착
이렇게 이동할 수 있나?
안될 것 같은데
서서히 증가하고 서서히 줄어들어야 할 듯
그리디?
한칸씩 줄어야 함
한칸씩...
1 3 3 3 3 1 -> 불가
1 2 2 2 2 1-> 가능 10
이렇게 할 바에 1 2 3 2 1 -> 10 최대 5번.
절반으로 나눠서?
-1을 쓰는 경우가 있을까? -> 없는거같은데
마지막 이동거리가 1이라면
그 이전 이동거리는 2를 초과할 수 없다...
-1 진짜 안쓰는거같음
반띵해야할듯
n^2 -> n+n-1 -> 2n-1
1 -> 1
2 -> 1 1
4 -> 3
1 2 1
9 -> 5
1 2 3 2 1 n = 3, n^2-1
0 10
10 -> 6
1 2 3 2 1 1 n=3, n^2
11
1 2 3 2 2 1
12 -> 6 -> 3: sqN
1 2 3 3 2 1 n=3, n^2
13 -> 7
1 2 3 3 2 1 1 n=3, n^2+n>13 -> n^2+1
14 -> 7 - sqrt(14) -> 3.xxx -> 4 * 2 + 1
1 2 3 3 2 2 1
15
1 2 3 3 3 2 1 n = 3, n^2 - 1
1 2 3
16 -> 7 -> n = 4, n^2 - 1
완전제곱이 되는 수는 N -> sqN
sqN*2-1 -> 최소이동거리
완전제곱이 아니면서 sqN^2+sqN >= num
sqN*2
완전제곱이 아니면서 sqN^2+sqN < num
sqN*2+1

1
1
2
1 1
3
1 1 1
4
1 2 1
5
1 2 1 1
6
1 2 2 1
1~ -> 1 ->
4~ -> 3
9 -> 5
16 -> 7 1 2 3 4 3 2 1
25 -> 9
1 2 3 4 5 4 3 2 1
26 ->
1 2 3 4 5 4 3 2 1 1
36
1 2 3 4 5 5 5 4 3 2 1
1 2 3 4 5 6 5 4 3 2 1
1
1
2
1 1
3
1 1 1
4
1 2 1

            2147483647
46340 ^ 2 = 2147395600
46341 ^ 2 = 2147488281
* */
fun main() = with(System.`in`.bufferedReader()) {
    var T = readLine().toInt()
    repeat(T) {
        var (x, y) = readLine().split(" ").map { it.toLong() }
        var num = y - x
        if (num < 4) {
            println(num)
        } else {
            var sqrtNum = sqrt(num.toDouble())
            // 제곱수가 아니라면
            if (sqrtNum != floor(sqrtNum)) {
                if (sqrtNum.toInt() * sqrtNum.toInt() + sqrtNum.toInt() < num)
                    println(sqrtNum.toInt() * 2 + 1)
                else {
                    println(sqrtNum.toInt() * 2)
                }
            }
            // 제곱수라면
            else {
                println(sqrtNum.toInt() * 2 - 1)
            }
        }
    }
}