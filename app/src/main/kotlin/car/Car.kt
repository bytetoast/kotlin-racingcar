package org.example.app.car

class Car(val name: String, val generator: RandomGenerator) {
    var position: Int = 0

    init {
        require(name.length in 1..5) { "자동차의 이름은 1자 이상 5자 이하여야 합니다." }
    }

    @JvmName("userDefined")
    fun getName(): String {
        return name
    }

    @JvmName("userDefined")
    fun getPosition(): Int {
        return position
    }

    fun move() {
        val randomVal = generator.generateRandomNumber()
        if (randomVal >= 4) {
            position++
        }
        println(name + " : " + "-".repeat(position))
    }
}