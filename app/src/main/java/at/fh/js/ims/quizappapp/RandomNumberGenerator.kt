package at.fh.js.ims.quizappapp

import kotlin.random.Random

class RandomNumberGenerator(private val minimum: Int, private val maximum: Int) {
    init {
        require(minimum < maximum) { "Minimum must be less than maximum" }
    }

    fun nextInt(): Int {
        return Random.nextInt(minimum, maximum + 1)
    }
}