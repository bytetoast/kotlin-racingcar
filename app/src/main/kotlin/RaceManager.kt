package org.example.app

class RaceManager() {
    var cars: ArrayList<Car> = ArrayList<Car>()

    fun getCars(): ArrayList<Car> {
        return cars
    }

    fun addCar(carName: String) {
        cars.add(Car(carName, 0))
    }
}
