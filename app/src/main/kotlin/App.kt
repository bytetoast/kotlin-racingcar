package org.example.app

import org.example.app.handler.RegistrationHandler
import org.example.app.manager.RaceManager

fun main() {
    val registrationHandler = RegistrationHandler()
    val raceManager = RaceManager()

    registrationHandler.startRegistration()
    raceManager.determineNumberOfRounds()
    raceManager.fetchCars(registrationHandler.getCars())
    raceManager.startRace()
    raceManager.displayWinners()
}
