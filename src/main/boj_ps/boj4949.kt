import java.util.Stack

fun main() = with(System.`in`.bufferedReader()) {
	var tempstr = readLine()

	var str = StringBuilder()

	while (tempstr.isNotEmpty() && !tempstr.equals(".")) {

		// tempstr 입력받기: 여러 줄에 걸쳐 들어올 수 있음
		while (!tempstr.equals(".") && tempstr.last() != '.') {
			tempstr += readLine()
		}

		var stack = Stack<Char>()

		var strlen = str.length

		for (i in tempstr.indices) {
			when (tempstr[i]) {
				'(' -> stack.push('(')
				'[' -> stack.push('[')
				')' -> if (stack.isEmpty() || stack.peek() != '(') {
					str.append("no\n")
					tempstr = ""
					break
				} else stack.pop()
				']' -> if (stack.isEmpty() || stack.peek() != '[') {
					str.append("no\n")
					tempstr = ""
					break
				} else stack.pop()
			}
		}


		if (!tempstr.equals(".") && strlen == str.length && stack.isEmpty()) {
			str.append("yes\n")
		} else if (stack.isNotEmpty() && tempstr.isNotEmpty()) str.append("no\n")

		tempstr = readLine()
		if (tempstr.equals(".\n") || tempstr.equals("."))
			break
	}

	print(str)
}