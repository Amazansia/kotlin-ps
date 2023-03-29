package kotlin_.키패드누르기

import java.lang.Math.abs

/*
손가락 이동 상하좌우
거리가 같다면 hand 사용
* */

class Solution {
    fun solution(numbers: IntArray, hand: String): String {
        var answer = StringBuilder("")

        var left = 3 to 0
        var right = 3 to 2
        for (i in numbers) {
            when (i) {
                1 -> {
                    left = 0 to 0
                    answer.append("L")
                }
                4 -> {
                    left = 1 to 0
                    answer.append("L")
                }
                7 -> {
                    left = 2 to 0
                    answer.append("L")
                }
                3 -> {
                    right = 0 to 2
                    answer.append("R")
                }
                6 -> {
                    right = 1 to 2
                    answer.append("R")
                }
                9 -> {
                    right = 2 to 2
                    answer.append("R")
                }
                2 -> { // 0 to 1
                    var rightDis = abs(right.first - 0) + abs(right.second - 1)
                    var leftDis = abs(left.first - 0) + abs(left.second - 1)
                    if (leftDis > rightDis) {
                        right = 0 to 1
                        answer.append("R")
                    } else if (leftDis < rightDis) {
                        left = 0 to 1
                        answer.append("L")
                    } else {
                        if (hand == "right") {
                            right = 0 to 1
                            answer.append("R")
                        } else {
                            left = 0 to 1
                            answer.append("L")
                        }
                    }
                }
                5 -> { // 1 to 1
                    var rightDis = abs(right.first - 1) + abs(right.second - 1)
                    var leftDis = abs(left.first - 1) + abs(left.second - 1)
                    if (leftDis > rightDis) {
                        right = 1 to 1
                        answer.append("R")
                    } else if (leftDis < rightDis) {
                        left = 1 to 1
                        answer.append("L")
                    } else {
                        if (hand == "right") {
                            right = 1 to 1
                            answer.append("R")
                        } else {
                            left = 1 to 1
                            answer.append("L")
                        }
                    }
                }
                8 -> { // 0 to 1
                    var rightDis = abs(right.first - 2) + abs(right.second - 1)
                    var leftDis = abs(left.first - 2) + abs(left.second - 1)
                    if (leftDis > rightDis) {
                        right = 2 to 1
                        answer.append("R")
                    } else if (leftDis < rightDis) {
                        left = 2 to 1
                        answer.append("L")
                    } else {
                        if (hand == "right") {
                            right = 2 to 1
                            answer.append("R")
                        } else {
                            left = 2 to 1
                            answer.append("L")
                        }
                    }
                }
                0 -> { // 0 to 1
                    var rightDis = abs(right.first - 3) + abs(right.second - 1)
                    var leftDis = abs(left.first - 3) + abs(left.second - 1)
                    if (leftDis > rightDis) {
                        right = 3 to 1
                        answer.append("R")
                    } else if (leftDis < rightDis) {
                        left = 3 to 1
                        answer.append("L")
                    } else {
                        if (hand == "right") {
                            right = 3 to 1
                            answer.append("R")
                        } else {
                            left = 3 to 1
                            answer.append("L")
                        }
                    }
                }
            }
        }
        return answer.toString()
    }
}