import kotlin.math.*

//package kotlin_.풍선터트리기
/*
풍선 n개를 일렬로 나열
1개 남을 때까지 터트린다
1. 인접한 두 풍선을 고르고 하나를 터트린다
	번호가 작은 풍선을 터트리는 행위는 최대 1번만 할 수 있다
2.풍선 사이에 빈 공간이 생겼다면 빈 공간이 없도록 중앙으로 밀착시킨다
풍선들을 하나만 남을 때까지 터트렸을 때 최후까지 남을 수 있는 풍선의 개수를 return
풍선 번호 배열 a가 주어진다
a의 size는 1000000 -> 시간복잡도 N으로 끝내야 할 듯? NlogN도 아슬아슬하다
a[i]의 범위는 -10억~10억
번호는 모두 유니크하다
k번째 풍선을 남기고 싶을 때
가정 1. k번째 풍선을 기준으로 0~k-1, k+1~n으로 배열을 나눴을 때 양 배열에서 가장 작은 값이 k번째 풍선의 번호보다 작으면 남길 수 없다.
양쪽에 배열이 없을 경우 언제나 가능하다
가정 1이 올바르다고 생각해 보자.
양쪽에서 반대쪽으로 진행하며 왼쪽/오른쪽 배열의 최솟값을 업데이트해 주자
left_min, right_min 배열을 만들고
for문을 돌면서 기타 예외처리: 왼쪽&오른쪽&양쪽에 배열이 없을 때를 핸들링해 주고
양쪽 배열의 값을 비교해서 a.size만큼 돌면서 조건에 맞는 풍선 개수 게산
* */

class Solution {
	fun solution(a: IntArray): Int {
		var answer: Int = 0

		var len = a.size

		if (a.size < 3)
			return a.size

		// left_min[i]: 0~i의 원소 중 최솟값
		var leftMin = IntArray(len)
		leftMin[0] = a[0]
		// right_min[i]: i~n의 원소 중 최솟값(역방향)
		var rightMin = IntArray(len)
		rightMin[len - 1] = a[len - 1]

		for (i in 1 until len) {
			leftMin[i] = min(leftMin[i - 1], a[i])
		}

		for (i in len - 2 downTo 0) {
			rightMin[i] = min(rightMin[i + 1], a[i])
		}

		answer += 2

		for (i in 1 until len - 1) {
			if (a[i] > leftMin[i - 1] && a[i] > rightMin[i+1])
				continue
			answer++
		}

		return answer
	}
}