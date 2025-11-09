package org.example.app

class RaceManager() {
    var cars: ArrayList<Car> = ArrayList<Car>()

    fun processRound() {
        for (car in cars) car.move()
        println("\n")
    }

    fun displayWinningResults() {
        var max = -1
        var winners = ArrayList<String>()
        for (car in cars) {
            if (car.getPosition() > max) {
                max = car.getPosition()
                winners.clear()
                winners.add(car.getName())
            } else if (car.getPosition() == max) {
                winners.add(car.getName())
            }
        }
        println("최종 우승자 : " + winners.joinToString(", "))
    }
}
