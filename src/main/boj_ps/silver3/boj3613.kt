package silver3
/*
변수명 바꾸기
자바: 첫단어 소문자, 다음단어 대문자, 붙여쓰기
C++: 소문자만 사용, 단어구분 _
둘다 아니면 error?
error 조건
1. 대문자&_ 함께 포함
2. _ 없으면서 모든 문자 소문자
* */

fun main() = with(System.`in`.bufferedReader()) {
    var name = readLine()
    // error 조건 1. 대문자, _ 함께 포함, 2. _ 없고 모든 문자 소문자
    if (name.contains("[A-Z]".toRegex()) && name.contains("_")
        || name.last() == '_'
        || name.first() == '_' || name.first().isUpperCase()
        || name.contains("__")
    ) {
        println("Error!")
        return@with
    }


    var answer = StringBuilder()
    // Java
    if (name.contains("[A-Z]".toRegex())) {
        name.forEach {
            answer.append(if (it.isUpperCase()) "_${it.lowercaseChar()}" else it)
        }
    }
    // C++
    else {
        var idx = 0
        while (idx < name.length) {
            if (name[idx] == '_') {
                answer.append(name[++idx].uppercaseChar())
            } else {
                answer.append(name[idx])
            }
            idx++
        }
    }
    println(answer)
}