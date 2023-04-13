package kotlin_.행렬테두리회전하기
/*
x1, y1, x2, y2가 주어지며 x1, y2부터 x2, y2까지 해당하는 직사각형에서 테두리의 숫자들을 한 칸씩 시계방향으로 이동한다.
인풋:
회전쿼리 최대 1만개
행렬 크기 최대 100*100
1번 회전시 접근하는 배열 최대 36, 즉 최대로 돌려도 36만...정도?
처음 행렬에는 1부터 숫자가 하나씩 증가하면서 적혀 있다.
즉, i,j -> (i-1) * columns + j
회전은 순서대로 이루어진다.
회전에 의해 위치가 바뀐 숫자 중 매 회전마다 가장 작은 숫자를 순서대로 배열에 담아 return
즉, return 배열의 길이는 회전 쿼리의 size와 같다
인풋값이 그렇게 크지 않고 매 회전마다 최솟값을 찾아내야 하므로 쌩구현이 맞다
1. 회전시키는 함수
2. 회전 이후 최솟값 찾는 함수
각각 구현해서 매 쿼리마다 돌리고 2번함수의 값 answer에 넣으면 될 듯
* */

class Solution {
    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        var answer = mutableListOf<Int>()

        // 배열 생성 & 초기화
        var matrix = Array(rows) { IntArray(columns) }
        var numValue = 1
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                matrix[i][j] = numValue++
            }
        }

        fun rotateIT(x1: Int, y1: Int, x2: Int, y2: Int) {
            var newArr = mutableListOf<Int>()
            for (i in y1..y2) {
                newArr.add(matrix[x1][i])
            }
            for (i in x1 + 1..x2) {
                newArr.add(matrix[i][y2])
            }
            for (i in y2 - 1 downTo y1) {
                newArr.add(matrix[x2][i])
            }
            for (i in x2 - 1 downTo x1 + 1) {
                newArr.add(matrix[i][y1])
            }

            println(newArr.joinToString(" "))

            var temp = newArr.last()
            newArr.add(0, temp)
            newArr.removeAt(newArr.lastIndex)
            answer.add(newArr.minOrNull() ?: -1)

            var idx = 0
            for (i in y1..y2) {
                matrix[x1][i] = newArr[idx++]
            }
            for (i in x1 + 1..x2) {
                matrix[i][y2] = newArr[idx++]
            }
            for (i in y2 - 1 downTo y1) {
                matrix[x2][i] = newArr[idx++]
            }
            for (i in x2 - 1 downTo x1 + 1) {
                matrix[i][y1] = newArr[idx++]
            }
        }

        for (query in queries) {
            rotateIT(query[0] - 1, query[1] - 1, query[2] - 1, query[3] - 1)
        }

        return answer.toIntArray()
    }
}