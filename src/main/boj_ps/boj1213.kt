fun main() = with(System.`in`.bufferedReader()) {
	var str = readLine()
	var alphabet = IntArray(26)
	for (i in str.indices) {
		alphabet[str[i].code - 'A'.code]++
	}
	var oddCount = 0
	var oddAlphabetloca = 0
	for (i in alphabet.indices) {
		if (alphabet[i] % 2 == 1) {
			oddCount++
			oddAlphabetloca = i
		}
	}

	if (oddCount > 1) {
		print("I'm Sorry Hansoo")
		return
	}

	// 반접 가능
	if (str.length % 2 == 0 && oddCount == 0) {
		var front = StringBuilder()
		for (i in alphabet.indices) {
			front.append(('A'.code + i).toChar().toString().repeat(alphabet[i] / 2))
		}

		var back = front.reversed()

		print(front.append(back))
		return
	}

	// 중간에 홀수 하나 넣어야 함
	if (str.length % 2 == 1 && oddCount == 1) {
		var front = StringBuilder()
		for (i in alphabet.indices) {
			front.append(('A'.code + i).toChar().toString().repeat(alphabet[i] / 2))
		}
		var back = front.reversed()

		front.append(('A'.code + oddAlphabetloca).toChar())

		print(front.append(back))
	}
}