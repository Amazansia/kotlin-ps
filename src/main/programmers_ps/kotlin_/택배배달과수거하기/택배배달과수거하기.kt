package kotlin_.택배배달과수거하기
/*
집 개수 n 10만
트럭 하나로 이동할 때 걸리는 최소 거리
cap이 허용할 때까지 최대 배달 & 최대 수거
1. 먼 거리 먼저 배달 & 수거: 이때 이동거리는 고정
엣지조심: 모든 딜리버리 & 픽업이 0일 수 있다
n-1부터 0까지 순회하며 체크
첫번째 시도: 배달에 필요한 거리 vs 픽업에 필요한 거리 중 큰 거리를 계산해서 리턴
두번째 시도: 배달 & 픽업 함께 고려
1. 뒤에서부터 순회하며 d[i] > 0 || p[i] > 0인 곳을 방문한다
1-1. d[i]부터 순회하며 cap만큼 방문한다
1-2. p[i]부터 순회하며 cap만큼 방문한다
2. lastdis는 d[i] == 0 && p[i] == 0일 때 감소시킨다
*/

class Solution {
	fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
		var answer: Long = 0
		var delSum = deliveries.sum()
		var picSum = pickups.sum()

		var lastdis = n

		while(lastdis > 0){
			// 해당 집을 방문해야 함
			if(deliveries[lastdis - 1] > 0 || pickups[lastdis - 1] > 0){
				// println("answer = $answer, lastdis = $lastdis")
				answer += lastdis * 2
				var c = cap

				if(delSum > 0){
					var ddis = lastdis
					while(c > 0 && ddis > 0){
						var temp = deliveries[ddis - 1]
						deliveries[ddis - 1] = kotlin.math.max(deliveries[ddis - 1] - c, 0)
						delSum -= temp - deliveries[ddis - 1]
						c = kotlin.math.max(c - temp, 0)
						if(deliveries[ddis - 1] == 0){
							ddis--
						}
					}
				}

				if(picSum > 0){
					c = cap
					var pdis = lastdis
					while(c > 0 && pdis > 0){
						var temp = pickups[pdis - 1]
						pickups[pdis - 1] = kotlin.math.max(pickups[pdis - 1] - c, 0)
						picSum -= temp - pickups[pdis - 1]
						c = kotlin.math.max(c - temp, 0)
						if(pickups[pdis - 1] == 0){
							pdis--
						}
					}
				}

				// println(deliveries.joinToString(" "))
				// println(pickups.joinToString(" "))
			}
			if(deliveries[lastdis - 1] == 0 && pickups[lastdis - 1] == 0) lastdis--

		}

		return answer
	}
}
