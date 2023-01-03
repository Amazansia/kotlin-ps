fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine().toInt()
	var arrDistance = readLine().split(" ").map { it.toInt() }.toIntArray()
	var arrStation = readLine().split(" ").map { it.toInt() }.toIntArray()
	var sum: Long = 0
	var minValue: Long = arrStation[0].toLong()
	for (i in arrStation.indices) {
		if (i == arrStation.size - 1) break
		if (arrStation[i] < minValue) {
			minValue = arrStation[i].toLong()
		}
		sum += minValue * arrDistance[i]
	}
	print(sum)
}