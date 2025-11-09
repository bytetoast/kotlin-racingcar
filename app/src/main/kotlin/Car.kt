package org.example.app

class Car(val name: String) {
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
        val randomVal: Int = (0..9).random()
        if (randomVal >= 4) {
            position++
        }
        println(name + " : " + "-".repeat(position))
    }
}
