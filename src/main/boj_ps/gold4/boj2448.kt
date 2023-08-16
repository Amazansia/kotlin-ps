package gold4

/*
재귀?
3줄짜리 125삼각형을 그리는 함수 smallTriangle()
3 * 2^10 -> 2048 * 3 = 6천얼마
가장 밑줄에 깔리는 125삼각형의 개수는 N/3개
맨 밑줄의 길이는 5*N/3(*) + N/3-1( )
맨 윗줄의 시작점은 (5*N/3 + N/3-1)/2 or (-1)

1. 가장 밑줄에 깔리는 125삼각형의 개수는 N/3개
2. 맨 밑줄의 길이는 5*N/3(*) + N/3-1( )
3. 125삼각형 밑에 125삼각형을 그릴 수 있는 조건:
부모삼각형 좌/우에 125삼각형이 존재한다면 해당 꼭짓점 밑으로는 125삼각형을 그릴 수 없다
* */

fun main() {

    var N = readln().toInt()

    var answer = Array(N) { CharArray(5 * N / 3 + N / 3 - 1) { ' ' } }

    fun drawingTriangleByLocation(x: Int, y: Int) {

        answer[x][y + 2] = '*'
        answer[x + 1][y + 1] = '*'
        answer[x + 1][y + 3] = '*'
        for (i in 0 until 5) {
            answer[x + 2][y + i] = '*'
        }
    }

    // 1의 위치 x, y를 기준으로 재귀: 재귀 시작점 x - 2, y
    fun putTriangleLocation(n: Int, x: Int, y: Int) {
        if (n == 3) {
            drawingTriangleByLocation(x, y)
            return
        }

        putTriangleLocation(n / 2, x, y + n / 2)
        putTriangleLocation(n / 2, x + n / 2, y)
        putTriangleLocation(n / 2, x + n / 2, y + n)
    }

    putTriangleLocation(N, 0, 0)

    var sb = StringBuilder()

    for (i in answer) {
        sb.append(i + '\n')
    }

    print(sb)
}