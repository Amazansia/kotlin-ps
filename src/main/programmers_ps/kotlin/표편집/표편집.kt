package kotlin.표편집

import java.util.*

/*
표의 행을 선택, 삭제, 복구하는 프로그램
"U X": 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
"D X": 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
"C" : 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
"Z" : 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.
모든 명령어를 수행한 후 표의 상태와 처음 주어진 표의 상태를 비교하여
삭제되지 않은 행은 O, 삭제된 행은 X로 표시
정답은 표의 0행부터 n - 1행까지에 해당되는 O, X를 순서대로 이어붙인 문자열 형태로 return 해주세요. -> joinToString()
처음 표의 행 개수를 나타내는 정수 n
처음에 선택된 행의 위치를 나타내는 정수 k
수행한 명령어들이 담긴 문자열 배열 cmd
n 10만, cmd 배열 size 20만이므로 N^N으로는 불가
대신 포인터 위치에 대한 적절한 조작이 필요함...
쌍을 이루는 C와 Z가 함께 삭제된다고 가정했을 때, 문제가 되는 것은 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택해야 하는 것
단순구현으로 해봅시다 -> 정확성 효율성 둘다 틀림
LinkedList로 구현해봅시다
["D ","C","U 3","C","D 4","C","U 2","Z","Z"]	"OOOOXOOO"
이거 입력케이스중 list가 아예 없는? 경우도 있나?
* */
class Solution {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {

        // 삭제된 원소를 넣는 스택
        var stack = Stack<Int>()

        var ptr = k
        var size = n

        for (i in cmd) {
            when (i[0]) {
                'U' -> {
                    ptr -= i.substring(2).toInt()
                }
                'D' -> {
                    ptr += i.substring(2).toInt()
                }
                'C' -> {
                    stack.push(ptr)
                    size--
                    if (ptr == size) ptr--
                }
                'Z' -> {
                    if (stack.pop() <= ptr) ptr++
                    size++
                }
            }
        }

        var answer = StringBuilder("O".repeat(size))
        while (stack.isNotEmpty()) {
            answer.insert(stack.pop().toInt(), 'X')
        }

        return answer.toString()
    }
}