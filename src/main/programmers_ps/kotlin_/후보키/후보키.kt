//package kotlin_.후보키
/*
후보키: 튜플을 유일하게 식별할 수 있는
유일성 & 최소성 둘다 만족
후보키의 개수 return
dfs로 조합 전부 구하고 모든 재귀호출에서 식별 검사하면 될 듯
visited all true면 자동 종료

세미 유니온파인드?
r ~20, c ~8

* */

class Solution {
    fun solution(relation: Array<Array<String>>): Int {
        var answer = 0

        // 속성에 대해 방문처리
        var visited = BooleanArray(relation[0].size)
        var list = mutableListOf<BooleanArray>()

        fun checkCandidateKey(visited: BooleanArray): Boolean {
            // 해당되는 속성만 뽑아서 keys에 저장
            // [100, ryan] [200, apeach] [300, tube] [400, con] [500, muzi] [600, apeach]
            var keys = relation.map { it.filterIndexed { index, _ -> visited[index] } }

            // isUnique
            for (key in keys) {
                if (keys.count { it == key } != 1)
                    return false
            }

            // isMinimal
            for (i in 0 until keys[0].size) {
                var testKeys = keys.map { it.filterIndexed { index, _ -> index != i } }
                // 뺐으면 유일성이 유지되면 안 된다...
                for (key in testKeys) {
                    if (testKeys.count { it == key } != 1) {
                        println(keys.joinToString(" "))
                        return true

                    }
                }
            }

            return false
        }

        fun dfs(visited: BooleanArray) {
            if (checkCandidateKey(visited)) {
                println(visited.joinToString(" "))
                answer++
            }

            for (i in visited.indices) {
                if (!visited[i]) {
                    visited[i] = true
                    dfs(visited)
                    visited[i] = false
                }
            }
        }

        dfs(visited)

        return answer
    }
}