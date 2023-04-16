package kotlin_.요격시스템
import java.util.*
import kotlin.Comparator

/*
모든 폭격 미사일을 관통하며 한번에 요격...
s~e 1억까지
targets 50만...
요격 미사일의 최솟값?
일단 정렬해야 할 듯
안겹쳐지면 쏴야함
우큐로
pop한 원소의 end > start인 원소를 싹 지우고
end 더 못쓰면 또 pop해야함
*/
class Solution {
	fun solution(targets: Array<IntArray>): Int {
		var answer: Int = 1

		var pq = PriorityQueue(compareBy<IntArray> { it[0] }.thenBy { it[1] })
		for (t in targets) {
			pq.add(t)
		}
		var now = pq.poll()
		while (pq.isNotEmpty()) {
			while (true) {
				if (pq.peek()[1] > now[1]) break
				pq.poll()
			}
			now = pq.poll()
			answer++
		}

		return answer
	}
}