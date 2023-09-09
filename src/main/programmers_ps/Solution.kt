/*
acts 1~3000
반복횟수 10^9

페이지 이동:
1. 현재 페이지를 뒤로 가기 스택에 저장
2. idx + 1페이지로 이동
3. 앞으로 가기 스택 모두 삭제
즉 뒤로 가기 스택에 현재 페이지~idx+반복수 페이지 저장, 앞으로 가기 스택 초기화

뒤로 가기
1. 앞으로 가기 스택에 현재 페이지 저장
2. 뒤로 가기 스택에서 가장 최근의 페이지를 현재 페이지로 가져옴
    2-1. 뒤로 가기 스택이 비면 모든 실행 무시
즉 앞으로 가기 스택에 현재 페이지~뒤로 가기스택[반복수] 페이지 저장, 뒤스택 없으면 실행안됨

앞으로 가기
1. 뒤로 가기 스택에 현재 페이지 저장
2. 앞으로 가기 스택에서 가장 최근의 페이지를 현재 페이지로 가져옴
    2-1. 앞으로 가기 스택이 비면 모든 실행 무시
즉 뒤로 가기 스택에 현재 페이지~앞으로 가기스택[반복수] 페이지 저장, 앞스택 없으면 실행안됨

idx가 따로있다; 전역변수로 둬야할듯
연속구간을 페어로 저장: 시작~끝을 페어로
앞스택은 항상 내림차순이고 뒤스택은 오름차순이다
*/

class Solution {
    fun solution(acts: Array<IntArray>): LongArray {
        var answer = LongArray(acts.size)

        var front = mutableListOf<Pair<Long, Long>>()
        var back = mutableListOf<Pair<Long, Long>>()

        var page = 0L
        var pageidx = 0L

        /*
        페이지 이동:
        1. 현재 페이지를 뒤로 가기 스택에 저장
        2. idx + 1페이지로 이동
        3. 앞으로 가기 스택 모두 삭제
        즉 뒤로 가기 스택에 현재 페이지+1~idx+반복수 페이지 저장, 앞으로 가기 스택 초기화
        * */
        fun moveToPage(n: Long, i: Int) {
//            back.add(page to page)
            page = pageidx + n
            back.add(pageidx to page - 1)
            pageidx = page

            answer[i] = page
            if (front.size != 0) {
                front = mutableListOf()
            }
        }

        fun gotoFront(n: Long, i: Int) {
            if (front.isEmpty()) {
                answer[i] = page
                return
            }

            var remain = n
            while (front.isNotEmpty() && remain != 0L) {
                var last = front.last()
                front.removeLast()

                // 한 구간 안에서 remain을 완전히 소화가능할때
                if (remain <= last.second - last.first + 1) {
                    var ls = last.first + remain
                    remain = 0
                    front.add(ls to last.second)
                    page = ls - 1

                    var backstart = if (back.size == 0) 0 else back.last().second + 1
                    back.add(backstart to page - 1)
                } else {
                    // remain 뺄수 있는데까지 빼보기
                    remain -= (last.second - last.first + 1)
                    back.add(page to last.second - 1)
                    page = last.second
                }
            }

            answer[i] = page
        }

        /*
        뒤로 가기
        1. 앞으로 가기 스택에 현재 페이지 저장
        2. 뒤로 가기 스택에서 가장 최근의 페이지를 현재 페이지로 가져옴
            2-1. 뒤로 가기 스택이 비면 모든 실행 무시
        즉 앞으로 가기 스택에 현재 페이지~뒤로 가기스택[반복수] 페이지 저장, 뒤스택 없으면 실행안됨
        */
        fun gotoBack(n: Long, i: Int) {
            if (back.size == 0) {
                answer[i] = page
                return
            }

            var remain = n
            while (back.isNotEmpty() && remain != 0L) {
                var last = back.last()
                back.removeLast()

                // back은 정순
                // remain 완전히 소화가능
                if (remain <= last.second - last.first + 1) {
                    var ls = last.second - remain
                    page = ls + 1
                    back.add(last.first to ls)

                    var frontend = if (front.size == 0) pageidx else front.last().second + 1
                    front.add(page + 1 to frontend)

                    remain = 0
                } else {
                    remain -= last.second - last.first + 1
                    front.add(last.first + 1 to page)
                    page = last.first
                }
            }

            answer[i] = page
        }

        for (i in acts.indices) {
            when (acts[i][0]) {
                0 -> {
                    moveToPage(acts[i][1].toLong(), i)
                    println("back: ${back.joinToString(" ")}")
                    println("page: $page, pageIdx: $pageidx")
                    println("front: ${front.joinToString(" ")}")
                    println()
                }

                1 -> {
                    gotoBack(acts[i][1].toLong(), i)
                    println("back: ${back.joinToString(" ")}")
                    println("page: $page, pageIdx: $pageidx")
                    println("front: ${front.joinToString(" ")}")
                    println()

                }

                else -> {
                    gotoFront(acts[i][1].toLong(), i)
                    println("back: ${back.joinToString(" ")}")
                    println("page: $page, pageIdx: $pageidx")
                    println("front: ${front.joinToString(" ")}")
                    println()

                }
            }
        }

        return answer
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    var acts = Array(5) { IntArray(2) }
    acts[0] = intArrayOf(0, 99)
    acts[1] = intArrayOf(2, 20)
    acts[2] = intArrayOf(1, 15)
    acts[3] = intArrayOf(0, 40)
    acts[4] = intArrayOf(0, 99)

//    // back/page/front/idx
//    acts[0] = intArrayOf(0, 3)
//    // 0,2/3/x/3
//
//    // 2칸 뒤로가기
//    acts[1] = intArrayOf(1, 2)
//    // 0,0/1/2,3
//
//    // 1칸 앞으로가기
//    acts[2] = intArrayOf(2, 1)
//    // 0,1/2/3,3
//
//    acts[3] = intArrayOf(0, 4)
//    acts[4] = intArrayOf(0, 10)
    var s = Solution()
    println(s.solution(acts).joinToString(" "))
}

