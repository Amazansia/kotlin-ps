/*
k개를 넘지 않는 차 있는 물병
같은 양의 물이 들어 있는 물병 2개를 고르고 하나의 물병에 물을 몰아 붓는다
N개로 K개를 넘지 않는 비어있지 않은 물병을 만드는 것이 불가능할 수 있다
N은 10000000, k는 1000
상점에서 사는 물병은 물이 1리터 들어 있다...
2048?
3 -> 1 1
4 -> 1 0
4 -> 2 0
4 -> 3 0
4 -> 4 0
5 -> 1 3
7 -> 1 1
5 -> 2 0
100으로 나올 수 있는 수의 최솟값
3

6 -> 1 2
6 -> 2 0 1 1 1 1 1 1 2 2 2 4 2
6 -> 3 0
6 -> 4 0
6 -> 5 0
6 -> 6 0
8 -> 1 0
2진수 변환?
16 1 -> 0
16 2
16 3
16 4
16 5
16 6 2 2 2 2 4 4
16 7 1 1 2 4 4 4
16 8
비어있지 않은 물병: 나머지
1000 1001
100000 1000
frontOneCount -> 1111 0000 0000 -> 4
f K
4 1
4 3 -> 1 0000 0000 0000
이 값이 K보다 크다면 len +1 더해주고... 빼준다
작다면
4 5 -> 걍 return 0
k보다 크다면 ...
* */
fun main() = with(System.`in`.bufferedReader()) {
	var (N, K) = readLine().trim().split(" ").map { it.toInt() }
	if (N <= K) {
		print(0)
		return@with
	}

	// N > K
	var numBinary = Integer.toBinaryString(N) ?: "0"
	var totalOne = numBinary.count { it == '1' }
	// 1111 0100 0010 0100 0000
	if (totalOne <= K) {
		print(0)
		return@with
	}
	var numlen = numBinary.length
	var frontOneCount = 0
	var firstZeroIdx = 0
	for (i in numBinary.indices) {
		if (numBinary[i] == '0') {
			firstZeroIdx = i
			var idx = i
			while(idx < numlen){

			}
			break
		}
		frontOneCount++
	}

	if (numBinary.substring(firstZeroIdx).contains('1')) {
		// frontOneCount > K && 뒤에 1이 있다면 자릿수 플러스해주기
		// 1111 1000 -> 5, K = 2 -> 1 0000 0000
		if (frontOneCount >= K) {
			var str = StringBuilder("0".repeat(numlen))
			str[0] = '1'
			str.append("0")
			var num = str.toString().toInt(2)
			println(num - N)
		}
		// frontOneCount <= K && 뒤에 1이 있다면
		// 1111 0000 -> 4 , K = 5 -> ok
		// 1101 2
		else {
			var str = StringBuilder("0".repeat(numlen - 1))
			str.replace(0, K - 1, "1".repeat(K))
//			println(str)

			var num = str.toString().toInt(2)
			println(num - N)
		}
	}
}