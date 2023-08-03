package gold1

import kotlin.math.*

/*
분할 개수의 최솟값 출력
최대길이 2500
N^2까지는 되는듯
0~N-1까지 돌면서
중앙값 기준으로 양쪽 인덱스 체크해서 i번째 원소를 중심으로 하는 가장 긴 펠린드롬 길이 저장
len[i]: i번째 원소를 중심으로 하는 가장 긴 펠린드롬 길이, 초기값은 무조건 1
len[3]
	ABACABA
len:1214121
dp :1211111
	   :len[3 - 4 + 1] ~ len[3 + 4 - 1] = 1
	   긴 것부터 선택
	   len이 만약
	   1214567이라면...

	   :dp[0]~dp[7]: dp[-1] + 1
	   업데이트하고 dp 456은 건너뜀 - while(cnt++ < size) cnt로
	   이다음 고려: dp[8] = len[]

dp[i]: i번째 원소까지 고려했을 때 펠린드롬 분할의 최솟값
	for(i in 0 until N) dp[i] = min(dp[i], dp[i-len[i]])
* */
/*
펠린드롬 분할 문자열 길이가 짝수일 경우에는 판별을 못함 ㅎ;
len[s] = e
s부터 시작, 가장 멀리 갈 때의 idx값 e인 펠린드롬 분할 문자열
걍 N^2으로 다찾아
그리고 제일 멀리가는거 확인해
분할갯수 체크해
끝
	0123456
	ABACABA
len:6543356
for i in 0 until 7 :
	i == 0
	answer++
	cnt += 6
	if(cnt >= 7) break
	탐색종료

dp로 돌면서
for i in 0 until N
	for j in 0 until i - 1
		if(isPalindrome(j, i)) dp[i] = min(dp[i], dp[j] + 1)
		dp[i] = dp[j] + isPalindrome(i, j)
* */

fun main() = with(System.`in`.bufferedReader()) {
	var str = readLine()
	var len = str.length

	fun isPalindrome(start: Int, end: Int): Boolean {
		if (start == end) return true
		var num = (end - start) / 2
		for (i in 0..num) {
			if (str[start + i] != str[end - i]) return false
		}
		return true
	}

	var dp = IntArray(len) { it + 1 }

	for (i in 0 until len) {
		for (j in 0..i) {

			if (isPalindrome(j, i)) {
				if (j == 0) {
					dp[i] = 1
					continue
				}
				dp[i] = min(dp[i], dp[j - 1] + 1)
			}
		}
	}
	println(dp[len - 1])
}