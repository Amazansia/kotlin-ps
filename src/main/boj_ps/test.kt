import kotlin.system.measureTimeMillis

fun main() {
	val numList1 = List(100_00000) { it }
	val numList2 = List(100_00000) { it }

	val time1 = measureTimeMillis {
		val sortedList = numList1.sortedDescending().toList()
	}
	println("time1 = $time1")

	val time2 = measureTimeMillis {
		val sortedList = numList2.asSequence().sortedDescending().toList()
	}
	println("time2 = $time2") // 책 내용대로라면 이게 더 느려야 되는데 더 빠르다..
}