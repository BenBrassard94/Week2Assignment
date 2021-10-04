import kotlin.random.Random

// Create a list of processes.
// Each process has 2 properties:
//   * it knows how much time has elapsed
//     between the time that the previous
//     process entered the waiting line
//     and the time it took its place
//     at the end of the line
//  * it knows the time at which it
//    joined the waiting line
//      * this value must be computed
//      * this computation requires a knowledge
//        of the time at which the previous
//        process joined the waiting line

// This program shows two ways of computing
// that second property.

data class Processes(
    val timeSinceArrival: Int,
    val timeOfArrivalCurrent: Int = 0
) // Process


fun main(args: Array<String>) {
    // Creates a random number generator
    val rand = Random(System.nanoTime())
    // Function that return random number drawn
    // from a distribution
    val randomNum = { rand.nextInt(1, 10)}

    val createProcess: (Int) -> Processes =
    { _ -> Processes(randomNum()) }

    val size = 10

    val queue = mutableListOf<Processes>()

    var delay = randomNum()
    queue.add(Processes(delay, delay))
    for (num in 1 until size) {
        delay = randomNum()
        var time = queue[num - 1].timeOfArrivalCurrent +
                delay
        queue.add(Processes(delay, time))
    } // for

    // Results
    for (process in queue)
        println(process)
} // main()