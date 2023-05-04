package gold4

import java.util.*

/*
진실 or 과장
어떤 사람이 어떤 파티에서는 진실을 듣고 다른 파티에서는 과장된 이야기를 들으면 거짓말쟁이
과장된 이야기를 할 수 있는 파티 개수의 최댓값을 구하시오...
사람 수 N, 파티 수 M
진실을 아는 사람의 수: truthNum
진실을 아는 사람들이 참석하는 파티를 제외한다. 그 파티에서는 과장하지 않는다.
1. 위 파티에 참석한 사람들을 Queue에 넣는다. -> 안될듯. 연쇄적으로 연결되어 있을 수 있으므로
again 1. union-find 사용
1 1
2 1 2
2 2 3
2 3 4
2 4 5
// 최적화...
1. 미리 Num list를 사람 수대로 배열에 만들어 놓고 사용한다?
2.
* */
fun main() = with(System.`in`.bufferedReader()) {

    class Num(num: Int) {
        var num: Int = num
        var parent: Num = this
    }

    fun find(v: Num): Num {
        if (v.parent != v) {
            v.parent = find(v.parent)
        }
        return v.parent
    }

    fun union(o1: Num, o2: Num) {
        var root1 = find(o1)
        var root2 = find(o2)
        if (root1.num == root2.num) return

        if (root1.num > root2.num) {
            root1.parent = root2
        } else {
            root2.parent = root1
        }
    }

    var (N, M) = readLine().trim().split(" ").map { it.toInt() }
    var answer = M

    var rline = readLine().trim().split(" ").map { it.toInt() }

    // 진실을 아는 사람이 없다면 바로 종료료
    if (rline[0] == 0) {
        println(M)
        return
    }

    // 사람들 리스트: 0 ~ N
    var peopleList = List(N + 1) { Num(it) }

    var truthList = rline.drop(1).sorted()
    for (t in truthList) {
        union(peopleList[truthList[0]], peopleList[t])
    }
    var partyList = LinkedList<List<Int>>()
    for (i in 0 until M) {
        partyList.add(readLine().trim().split(" ").map { it.toInt() }.drop(1))
    }

    // 진실을 알고 있는 사람들 모두 모으기
    for (i in 0 until M) {
        for (party in partyList) {
            for (p in party) {
                if (find(peopleList[p]) == find(peopleList[truthList[0]])) {
                    for (pp in party) {
                        union(peopleList[pp], peopleList[truthList[0]])
                    }
                    break
                }
            }
        }
    }


    for (party in partyList) {
        for (p in party) {
            // 파티에 진실을 아는 사람이 있음
            if (find(peopleList[p]) == find(peopleList[truthList[0]])) {
                answer--
                break
            }
        }
    }



    println(answer)


//
//    var removeList = mutableListOf<Int>()
//
//    for (party in partyList) {
//        for (t in truthList) {
//            // 파티에 참석한 사람들을 모두 truthlist에 넣어야 함
//            if (party.contains(t.num)) {
//                for (i in party) {
//                    if (truthList.find { it.num == i } == null) {
//                        var newAdd = Num(i)
//                        truthList.add(newAdd)
//                        union(truthList[0], newAdd)
//                    }
//                }
//                break
//            }
//        }
//    }
//
//    for (party in partyList) {
//        for (i in party) {
//            if (truthList.find { it.num == i } != null) {
//                answer--
//                break
//            }
//        }
//    }

}