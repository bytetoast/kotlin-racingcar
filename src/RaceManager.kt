class RaceManager() {
    var cars: ArrayList<Car> = ArrayList<Car>()

    fun getCars(): ArrayList<Car> {
        return cars
    }

    fun addCar(carName: String) {
        cars.add(Car(carName, 0))
    }

    fun processRound() {
        for (car in cars) car.move()
        println("\n")
    }

    fun getWinners() {
        val max = -1
        val winners = ArrayList<String>()
        for (car in cars) {
            if (car.getPosition() > max) {
                max = car.getPosition()
                winners.clear()
                winners.add(car.getName())
            } else if (car.getPosition() == max) {
                winners.add(car.getName())
            }
        }
        return java.lang.String.join(", ", winners)
    }
}
