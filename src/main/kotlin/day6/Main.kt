package day6

fun main(args: Array<String>) {

    val time:Long =  40929790
    val dist:Long = 215106415051100
    var possibleWays = 0

    for (holdTime in 1..time) {
        val reachedDistance = holdTime * (time - holdTime)
        if (reachedDistance >= dist) {
            possibleWays++
        }
    }

    println(possibleWays)
}