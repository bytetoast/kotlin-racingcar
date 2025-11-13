package org.example.app.car

class DefaultRandomGenerator : RandomGenerator {
    override fun generateRandomNumber(): Int {
        return (0..9).random()
    }
}