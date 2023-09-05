data class Person(var name: String, var age: Int)

fun main() = with(System.`in`.bufferedReader()) {


	val person = Person("", 0)
	var result = person.also {
		it.name = "James"
		it.age = 56
	}

	println("$result")
	println("$person")


}