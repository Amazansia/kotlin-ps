package silver4

import java.util.Stack

fun main() = with(System.`in`.bufferedReader()) {
	val K = readLine().toInt()
	var arr = Stack<Int>()
	for (i in 0 until K) {
		var num = readLine().toInt()
		if (num != 0) arr.add(num)
		else arr.pop()
	}
	println(arr.sum())
}