class Car(var name: String, var position: Int) {
    fun getName(): String {
        return name
    }

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
