//package kotlin_.후보키
/*
후보키: 튜플을 유일하게 식별할 수 있는
유일성 & 최소성 둘다 만족
후보키의 개수 return
dfs로 조합 전부 구하고 모든 재귀호출에서 식별 검사하면 될 듯
visited all true면 자동 종료

세미 유니온파인드
parent[i]에 부모 index 저장
r ~20, c ~8
-> 이렇게 하면 키 하나가 여러 조합에 사용될 때 올바르게 판별할 수 없음 쥐엔장~!
유파가 아니네용.
* */

class Solution {
	fun solution(relation: Array<Array<String>>): Int {
		var answer = 0

		// 속성에 대해 방문처리
		var visited = BooleanArray(relation[0].size)
//		var parent = IntArray(relation[0].size) { -1 }
//
//		// 세미 유니온파인드
//		// 가장 마지막에 나오는 속성의 index ->  parent[index] = t
//		fun union(visited: BooleanArray) {
//			for (i in visited.indices) {
//				if(visited[i] && parent[i] == -1)
//					parent[i] = t
//			}
//			t++
//		}

		fun checkCandidateKey(visited: BooleanArray): Boolean {
			// 해당되는 속성만 뽑아서 keys에 저장
			// [100, ryan] [200, apeach] [300, tube] [400, con] [500, muzi] [600, apeach]
			var keys = relation.map { it.filterIndexed { index, _ -> visited[index] } }
//			println(keys.joinToString(" "))
//			 isUnique: 식별가능
			for (key in keys) {
				if (keys.count { it == key } != 1) {
//					println("cc")
//					println(key)
					return false
				}
			}

//			// isDoublechecked
//			for (i in visited.indices) {
//				if (visited[i] && parent[i] != -1)
//					return false
//			}

			// isMinimal
//			println(visited.joinToString(" "))
//			println(visited.count { it })
			if (visited.count { it } == 1) {
//				println("isminimal???")
				return true
			}

			var count = 0
			for (i in 0 until keys[0].size) {
				var testKeys = keys.map { it.filterIndexed { index, _ -> index != i } }
				// 뺐으면 유일성이 유지되면 안 된다...
				for (key in testKeys) {
					if (testKeys.count { it == key } != 1) {
						// 유일성 유지 실패 -> 즉 정답인 조합
//						println(keys.joinToString(" "))
//						union(visited)
//						println(parent.joinToString(" "))
						count++
						break
					}
				}
			}

//			println("here ffffffff$count")

			return count == visited.count { it }
		}

		fun dfs(visited: BooleanArray, now: Int) {
//			println(visited.joinToString(" "))
			if (checkCandidateKey(visited)) {
//				println("answer nextline")
//				println(visited.joinToString(" "))
				answer++
				return
			}

			for (i in visited.indices) {
				// 순서가 중요하지 않다 -> 1 3은 3 1과 같다. 즉, 중복될 수 있으니 순서대로 세어야 한다
				if (!visited[i] && now <= i) {
					visited[i] = true
					dfs(visited, i)
					visited[i] = false
				}
			}
		}

		dfs(visited, 0)

		return answer
	}
}