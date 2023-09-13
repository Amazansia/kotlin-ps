package gold1

/*
산책시작 후 D분에는 정보과학관에 도착해야 한다
인접한 다른 건물로 이동하는데 1분이 걸린다.
D는 10억
사이클 가능...
DP
정보과학관에서 출발 -> 정보과학관에 도착
이거 왜 정답비율 85인것?
N이 10억번 돌아도 되나요...안될듯한데
"가능한 경로의 수"
t 1
정보-전산
정보-미래

-> 2
t 2
정보-전산-정보
정보-전산-신양
정보-전산-미래

정보-미래-정보
정보-미래-한경
정보-미래-신양
정보-미래-전산

-> 7
t 3
정보-전산-정보-전산
정보-전산-정보-미래

정보-전산-신양-전산
정보-전산-신양-미래
정보-전산-신양-진리
정보-전산-신양-한경

정보-전산-미래

정보-미래-정보
정보-미래-한경
정보-미래-신양
정보-미래-전산
...


정보 -> 전산/미래
전산 -> 정보/미래/신양
미래 -> 정보/전산/신양/한경
신양 -> 전산/미래/진리/한경
한경 -> 미래/신양/진리/형남
진리 -> 신양/한경/학생
학생 -> 진리/형남
형남 -> 학생/한경

int[8]짜리 배열만들어서
각 초마다 더해주기?
n으로 떨어지면 시초날듯

정보->전산으로 가는 가짓수 1
정보->미래로 가는 가짓수 1

행렬이었네요
해산~
* */

fun main() = with(System.`in`.bufferedReader()) {
    var N = readln().toInt()
    val MOD = 1000000007

    // ㅋㅋ
    var matrix = arrayOf(
        intArrayOf(0, 1, 1, 0, 0, 0, 0, 0),
        intArrayOf(1, 0, 1, 1, 0, 0, 0, 0),
        intArrayOf(1, 1, 0, 1, 1, 0, 0, 0),
        intArrayOf(0, 1, 1, 0, 1, 1, 0, 0),
        intArrayOf(0, 0, 1, 1, 0, 1, 0, 1),
        intArrayOf(0, 0, 0, 1, 1, 0, 1, 0),
        intArrayOf(0, 0, 0, 0, 0, 1, 0, 1),
        intArrayOf(0, 0, 0, 0, 1, 0, 1, 0)
    )

    fun mul(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
        var res = Array(8) { IntArray(8) }
        for (i in 0 until 8) {
            for (j in 0 until 8) {
                for (k in 0 until 8) {
                    res[i][j] = ((res[i][j].toLong() + A[i][k].toLong() * B[k][j].toLong()) % MOD).toInt()
                }
                res[i][j] %= MOD
            }
        }

        return res
    }

    fun cal(A: Array<IntArray>, n: Int): Array<IntArray> {
        if (n == 1) return A
        var cal2 = cal(A, n / 2)
        if (n % 2 == 0) return mul(cal2, cal2)
        return mul(mul(cal2, cal2), A)
    }

    var answer = cal(matrix, N)
    println(answer[0][0])
}