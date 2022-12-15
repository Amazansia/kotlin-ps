// 노드의 개수가 미리 주어졌고, 이름 또한 'A부터 차례대로 알파벳 대문자'로 주어졌기 때문에
// 트리를 선언 시 초기화시켜주는 것이 효율적이다.
fun main() = with(System.`in`.bufferedReader()) {

	data class Node(
		var name: Char,
		var left: Node? = null,
		var right: Node? = null,
	)

	val N = readLine().toInt()

	val tree = mutableListOf<Node>()
	for (i in 0 until N) {
		tree.add(Node('A'.plus(i)))
	}

	for (i in 0 until N) {
		var charlist = readLine().split(" ").map { it.single() }
		if (charlist[1] != '.') {
			tree[charlist[0] - 'A'].left = tree[charlist[1] - 'A']
		}
		if (charlist[2] != '.') {
			tree[charlist[0] - 'A'].right = tree[charlist[2] - 'A']
		}
	}

	fun preOrder(n: Node?, sb: StringBuilder) {
		n ?: return

		sb.append(n.name)
		preOrder(n.left, sb)
		preOrder(n.right, sb)
	}

	fun inOrder(n: Node?, sb: StringBuilder) {
		n ?: return

		inOrder(n.left, sb)
		sb.append(n.name)

		inOrder(n.right, sb)
	}

	fun postOrder(n: Node?, sb: StringBuilder) {
		n ?: return

		postOrder(n.left, sb)
		postOrder(n.right, sb)
		sb.append(n.name)

	}

	var sb = StringBuilder()
	preOrder(tree[0], sb)
	print("$sb\n")
	sb.setLength(0)
	inOrder(tree[0], sb)
	print("$sb\n")
	sb.setLength(0)
	postOrder(tree[0], sb)
	print(sb)
}


