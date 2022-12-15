fun main() = with(System.`in`.bufferedReader()) {
	val (N, K) = readLine().split(" ").map { it.toInt() }.toIntArray()

	var female_arr = IntArray(7)
	var male_arr = IntArray(7)

	var room = 0

	for (i in 0 until N) {
		var (gender, grad) = readLine().split(" ").map { it.toInt() }.toIntArray()
		if (gender == 0) female_arr[grad]++
		else male_arr[grad]++
	}

	for (i in 1..6) {
		room += female_arr[i] / K + male_arr[i] / K
		if (female_arr[i] % K != 0) room++
		if (male_arr[i] % K != 0) room++
	}

	print(room)
}