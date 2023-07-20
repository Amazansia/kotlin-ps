import kotlin.system.measureTimeMillis

class IntArrayWrapper(private val array: IntArray) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is IntArrayWrapper) return false

		return array.contentEquals(other.array)
	}

	override fun hashCode(): Int {
		return array.contentHashCode()
	}
}

fun main() {
	val N = 10000000
	val setWrapperArray = HashSet<IntArrayWrapper>()
	val setOriginList = HashSet<List<Int>>()

	// IntArray WrapperClass, set에 N개의 원소 추가하기
	val timeWrapperArray = measureTimeMillis {
		for (i in 0 until N) {
			setWrapperArray.add(IntArrayWrapper(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
		}
	}
	println(setWrapperArray.size)
	println("IntArrayWrapper = $timeWrapperArray")

	val timeOriginList = measureTimeMillis {
		for (i in 0 until N) {
			setOriginList.add(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
		}
	}
	println(setOriginList.size)
	println("List<Int> = $timeOriginList")

	var list = mutableListOf<List<Int>>()
	val timeOriginListAdd = measureTimeMillis {
		for (i in 0 until N) {
			list.add(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
		}
	}
	println(list.size)
	println("List<Int> mutableList Add = $timeOriginListAdd")
}