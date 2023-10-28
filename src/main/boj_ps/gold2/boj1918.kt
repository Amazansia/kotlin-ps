package gold2

import java.util.*

/*
주어진 중위 표기식을 연산자 우선순위에 따라 괄호로 묶어준다.
괄호 안의 연산자를 괄호 오른쪽으로 옮겨준다.
스택 자료구조...?
a+b*c-d/e
a+(b*c)-(d/e)
((a+(b*c))-(d/e))
a+(bc*)
abc*+de/-
A[]B -> AB[]
A를 기준으로 B와 []의 위치를 바꾼다
재귀?
만약 B 사이에 괄호 or 우선순위가 높은 연산자가 들어간다면 먼저 처리한다
음...
맞는듯
재귀나 스택인듯
알파벳은 무조건 한자리씩
A[]B를 스택처리한다고 생각하면
A는 넣지말고 그대로 이어붙이고
[]B 넣고 pop pop
그럼 B[]순으로 나오니까
이와중에 B에 괄호같은거 들어가있으면 다시 스택처리
A[](B+C)
[](B+C)
(B+C)[]
((B+C)*D) 이따위면?
스택에계속넣어?
A(B+C)[]
ABC+[]

재귀합시다
1. 괄호처리
2. * & / 처리
3. + & - 처리
* */
fun main() = with(System.`in`.bufferedReader()) {
    var str = readLine()

    print(recursive(str, 0))
}

fun recursive(str: String, startIdx: Int): String {
    if
}
