package org.example.app

class Car(var name: String, var position: Int) {
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
