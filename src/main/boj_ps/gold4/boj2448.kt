package gold4
/*
재귀?
3줄짜리 125삼각형을 그리는 함수 smallTriangle()
3 * 2^10 -> 2048 * 3 = 6천얼마
가장 밑줄에 깔리는 125삼각형의 개수는 N/3개
맨 밑줄의 길이는 5*N/3(*) + N/3-1( )
맨 윗줄의 시작점은 (5*N/3 + N/3-1)/2 or (-1)
125삼각형 옆에 125삼각형이 또 있으면 해당 꼭짓점 밑으로는 삼각형을 그리지 않는다
음 아닌데

* */

fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toInt()
}