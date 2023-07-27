package silver2

/*
괄호를 적절히 쳐서 식의 값을 최소로 만들기
덧셈되는 값들은 최소로,
- 이후의 값은 최대로 만들어야 함
덧셈에는 괄호쳐도 소용없다
- 기준으로 생각해야 함...
1 - 2 + 4 - 10 + 20
- 이후를 최대한 큰 숫자로
1 - (2 + 4) - (10 + 20)
- 기준으로 자르면?
1 / 2 + 4 / 10 + 20
55 / 50+40
* */
fun main() = with(System.`in`.bufferedReader()) {
	var str = readLine()

	var answer = 0

	if (str.contains('-')) {
		var nums = str.split('-')

		var flag = true

		for (num in nums) {
			var temp = 0
			if (num.contains('+')) {

				for (i in num.split('+')) {
					temp += i.toInt()
				}
			} else {
				temp = num.toInt()
			}

			if (flag) {
				answer += temp
				flag = false
			} else {
				answer -= temp
			}
		}

	} else {
		var nums = str.split('+')

		for (num in nums) {
			answer += num.toInt()
		}
	}

	println(answer)
}