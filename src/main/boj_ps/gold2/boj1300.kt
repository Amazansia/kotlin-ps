package gold2

import java.lang.Math.min

/*
N이 100000까지 들어옴
일차원 배열 B의 길이와 내부 배열의 원소 크기는 1부터 10_000_000_000 ㄷㄷ
a[i][j] = i * j
B[k]를 기준으로 이분탐색
B[k]인지 어떻게 확인하지?
n: 2
1 2
2 4
1 2 2 4
n: 3
1 2 3
2 4 6
3 6 9
1 2 2 3 3 4 6 6 9
n: 4
1 2 3 4
2 4 6 8
3 6 9 12
4 8 12 16
1 2 2 3 3 4 4 4 6 6 8 8 9 12 12 16

A[i][j]
mid=15
n: 5
1 2 3 4 5 1의 배수 5
2 4 6 8 10 2의 배수 5
3 6 9 12 15 5
4 8 12 16 20 3
5 10 15 20 25 3
21개가 15
15 = b[21]
1 2 2 3 3 4 4 4 5 5 6 6 8 8 9 10 10 12 12 15 15 16 20 20 25

1
2 4
3 6 9
4 8 12 16
5 10 15 20 25
for(i in 1 .. N)
	for(j in 1 .. i)
범위는 1~N^2
수는 대칭적으로 나타나므로 모두 두번씩 셀 필요가 없음
마지막 제곱수(i == j인 경우)를 제외하고 두 번씩 넣어주면 됨
1 2 2 4 -> 4
1 2 2 3 3 4 6 6 9...
이 수가 B[k]가 될 수 있는가를 판정할 수 있어야 함
걍 다 세버리자
* */

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
    var k = readLine().toInt()

    var low = 1L
    var high = k.toLong()

    while (low < high) {
        var mid = (low + high) / 2
        var cnt = 0L

        for (i in 1..N) {
            cnt += min(mid / i, N.toLong())
        }

        if (k <= cnt)
            high = mid
        else
            low = mid + 1
    }

    print(low)
}