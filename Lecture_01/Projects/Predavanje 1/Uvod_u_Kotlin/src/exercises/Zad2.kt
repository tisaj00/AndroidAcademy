import kotlin.random.Random

fun main(){


    var arrayOfInts: IntArray = IntArray(100) { Random.nextInt(1,100)}
    println("Size of array " + arrayOfInts.size)
    arrayOfInts.forEach {
        if(it<=10) {
            print(it)
            print(" , ")
        }
    }
    println()


}