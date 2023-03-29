fun main() = with(System.`in`.bufferedReader()) {
    open class Person(val name: String, val age: Int) {
        open fun cry() {
            print("person cry")
        }
    }

    class Student(name: String, age: Int, val id: Int) : Person(name, age) {
        override fun cry() {
            print("student cry")
        }
    }

    // 업캐스팅
    val person: Person = Student("J", 32, 20171218)
    // 다운캐스팅
    val student = person as Student
    print(student.id)
}