package org.example.app

fun main() {
    val registrationHandler = RegistrationHandler()
    val raceManager = RaceManager()

    registrationHandler.startRegistration()
    raceManager.determineNumberOfRounds()
    raceManager.fetchCars(registrationHandler.getCars())
    raceManager.startRace()
    raceManager.displayWinningResults()
}
