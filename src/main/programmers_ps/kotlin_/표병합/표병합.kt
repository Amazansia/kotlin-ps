package kotlin_.표병합
/*
표 크기 50 * 50
업데이트 r c value
업데이트 val1 val2
머지 -> r1 c1 & r2 c2 위치의 셀을 병합
위치 같으면 무시
두 셀 중 하나만 값이 존재할 경우 병합된 셀은 그 값을 가지게 된다
두 셀 모두 값이 존재할 경우 r1 c1의 값을 따라감
이후 r1 c1, r2 c2의 쿼리는 r1 c1으로 접근함
언머지 -> r c 위치의 셀에 걸린 모든 병합을 해제함
프린트
쿼리 개수 1000개
완탐으로 가능..
프린트는 중간에 낄 수도 있다
표 저장: 2차원 String 배열
병합 기록:
1. Pair를 검색할 수 있어야 함
2. pair는 집합으로 묶여 있을 수 있다 (크기 미정)
유니온파인드?

(1,2), (1,3), (1,4)
* */

class Solution {
    fun solution(commands: Array<String>): Array<String> {
        var answer = mutableListOf<String>()

        class Ver(rt: Int, ct: Int) : Comparable<Ver> {
            var r = rt
            var c = ct
            var parent: Ver = this
            var value = ""
            override fun compareTo(other: Ver): Int {
                return compareValuesBy(this, other, { it.r }, { it.c })
            }

            override fun toString(): String {
                return "[$r, $c, $value]"
            }
        }

        fun find(v: Ver): Ver {
            if (v != v.parent) {
                v.parent = find(v.parent)
            }
            return v.parent
        }

        fun union(v1: Ver, v2: Ver) {
            var v1Root = find(v1)
            var v2Root = find(v2)
            var rootString = v1Root.value.ifBlank { v2Root.value }

            if (v1Root != v2Root) {
                v2Root.parent = v1Root
                v1Root.value = rootString
                v2Root.value = ""
            }
        }

        var list = mutableSetOf<Ver>()

        for (command in commands) {
            var com = command.split(" ")
            when (com[0]) {
                "UPDATE" -> {
                    if (com.size == 4) {
                        var temp = list.find { it.r == com[1].toInt() && it.c == com[2].toInt() } ?: Ver(
                            com[1].toInt(),
                            com[2].toInt()
                        )
                        // temp가 다른 노드와 연결되어 있을 때
                        if (find(temp) != temp) {
                            find(temp).value = com[3]
                        }
                        // 연결되어 있지 않을 때
                        else {
                            temp.value = com[3]
                            list.add(temp)
                        }
                    } else {
                        for (v in list) {
                            if (v.value == com[1])
                                v.value = com[2]
                        }
                    }
                }
                "MERGE" -> {
                    var o1 = list.find { it.r == com[1].toInt() && it.c == com[2].toInt() } ?: Ver(
                        com[1].toInt(),
                        com[2].toInt()
                    )
                    var o2 = list.find { it.r == com[3].toInt() && it.c == com[4].toInt() } ?: Ver(
                        com[3].toInt(),
                        com[4].toInt()
                    )
                    if (find(o1) == find(o2)) continue
                    if (list.find { it.r == com[1].toInt() && it.c == com[2].toInt() } == null) {
                        list.add(o1)
                    }
                    if (list.find { it.r == com[3].toInt() && it.c == com[4].toInt() } == null) {
                        list.add(o2)
                    }
                    union(o1, o2)

                }
                "UNMERGE" -> {
                    var root = list.find { it.r == com[1].toInt() && it.c == com[2].toInt() } ?: Ver(
                        com[1].toInt(),
                        com[2].toInt()
                    )

                    var saveValue = find(root).value

                    var removelist = mutableListOf<Ver>()

                    for (i in list) {
                        if (find(i) == find(root)) {
                            removelist.add(i)
                        }
                    }
                    for (i in removelist) {
                        list.remove(i)
                    }

                    var newAdd = list.find { it.r == com[1].toInt() && it.c == com[2].toInt() } ?: Ver(
                        com[1].toInt(),
                        com[2].toInt()
                    )
                    newAdd.value = saveValue

                    list.add(newAdd)
                }
                "PRINT" -> {
                    var str = find(list.find { it.r == com[1].toInt() && it.c == com[2].toInt() } ?: Ver(
                        com[1].toInt(),
                        com[2].toInt()
                    )).value
                    answer.add(if (str != "") str else "EMPTY")
                }
            }
        }
        return answer.toTypedArray()
    }
}