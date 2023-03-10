package kotlin

/*
튜플
1. 중복가능
2. 순서가 다르면 다른 튜플
3. 튜플의 원소 개수는 유한하다

원소 개수가 N개이고 중복되는 원소가 없는 튜플을 나타내는 집합이 담긴 문자열 s가 매개변수로 주어질 때,
s가 표현하는 튜플을 배열에 담아 return
s의 길이는 1백만 이하...
"{{2},{2,1},{2,1,3},{2,1,3,4}}"	-> [2, 1, 3, 4]
"{{1,2,3},{2,1},{1,2,4,3},{2}}" -> [2, 1, 3, 4]
중복 체크를 어떻게 하느냐가 관건일 듯
튜플의 원소는 10만까지
사이즈가 작은 순 정렬
* */
fun main() = with(System.`in`.bufferedReader()) {
    class Solution {
        fun solution(s: String): IntArray {

            var str = s
            var arr = str.substring(2, s.length - 2).split("},{")
            //  원소 length순 정렬: 오름차순
            arr.sortedWith { o1: String, o2: String -> o1.length - o2.length }
            // arr 순회 - i: "1, 2, 3"
            var intset = mutableSetOf<Int>()
            for (i in arr) {
                var arrInt = i.split(",").map { it.toInt() }.toIntArray()
                for (j in arrInt) {
                    intset.add(j)
                }
            }

            return intset.toIntArray()
        }
    }
}

