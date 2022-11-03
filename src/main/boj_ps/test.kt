import java.awt.*


fun main() {
	var swan = Array<Point>(2) { Point() }
	var swan2 = arrayOfNulls<Point>(2)
	var swan3 = arrayOf(Point())

	print(swan[0])

	swan.set(0, Point(0, 0))

	swan3[0] = Point(1, 0)
	print(swan3[0])

	print(swan3.size)
	swan3[1] = Point(8, 7)
	print(swan3[1])

	print(swan3.size)

}