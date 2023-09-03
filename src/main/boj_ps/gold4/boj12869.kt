package gold4

/*
첫딜 9 두번째 3 세번째 1
체력 0 이하일 때 scv 파괴
N == 1이면 9로 나누고 나머지 존재할 경우 +1한 값이 정답
N == 2면 pq로 내림차순 정렬하고 9 3씩 딜넣어보면서 최솟값 구하기
N == 3이면...
내림차순 정렬한다고 무조건 모든 조건에 맞게 동작하는가?
10 9 1은?
1.
9 6 0
0 3 0
0 0 0
2.
1 9 1
0 0 0
내림차순 정렬로는 안된다
dfs 써야함
순서조합 6개 다 돌려봐야됨
즉 시복 6^N
N==2일때...즉 arr 마지막 원소를 0으로 놓고 걍 돌리면 되는거네 ㅠ

* */

fun main() = with(System.`in`.bufferedReader()) {
    var N = readln().toInt()
    var arr = readLine().split(" ").map { it.toInt() }.toIntArray()

    var answer = Int.MAX_VALUE

    fun dfs(n1: Int, n2: Int, n3: Int, cnt: Int) {

        if (cnt > answer) return
        if (n1 <= 0 && n2 <= 0 && n3 <= 0) {
            answer = kotlin.math.min(answer, cnt)
            return
        }

        if (n1 > 0) {
            dfs(n1 - 9, n2 - 3, n3 - 1, cnt + 1)
            dfs(n1 - 9, n2 - 1, n3 - 3, cnt + 1)
        }
        if (n2 > 0) {
            dfs(n1 - 3, n2 - 9, n3 - 1, cnt + 1)
            dfs(n1 - 1, n2 - 9, n3 - 3, cnt + 1)
        }
        if (n3 > 0) {
            dfs(n1 - 3, n2 - 1, n3 - 9, cnt + 1)
            dfs(n1 - 1, n2 - 3, n3 - 9, cnt + 1)
        }
    }

    dfs(arr[0], if (arr.size == 2) arr[1] else 0, if (arr.size == 3) arr[2] else 0, 0)
    println(answer)

}