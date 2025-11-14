package org.example.app.manager

import org.example.app.manager.NumRoundsState
import org.example.app.car.Car

class RaceManager() {
    var cars: ArrayList<Car> = ArrayList<Car>()
    var numRoundsState = NumRoundsState.IDLE
    var numRounds: Int = 0

    fun determineNumberOfRounds() {
        println("시도할 횟수는 몇 회인가요?")
        numRoundsState = NumRoundsState.WAITING
        awaitValidNumRoundsInput()
    }

    fun awaitValidNumRoundsInput() {
        while (waitingForNum()) {
            val numRoundsRaw = readln()
            validateNumRounds(numRoundsRaw)
        }
    }

    fun waitingForNum(): Boolean {
        return numRoundsState != NumRoundsState.SUCCESS
    }

    fun validateNumRounds(numRoundsRaw: String) {
        var numRoundsConverted: Int = 0
        try {
            matchesNumFormat(numRoundsRaw)
            numRoundsConverted = convertToInt(numRoundsRaw)
        } catch (e: IllegalArgumentException) {
            numRoundsState = NumRoundsState.ERROR
            println(e.message + "\n올바른 형식으로 다시 입력해주세요.")
            return
        }
        numRoundsState = NumRoundsState.SUCCESS
        numRounds = numRoundsConverted
    }

    fun matchesNumFormat(numRoundsRaw: String) {
        val numPattern = Regex("^[1-9][0-9]*$")
        if (!numPattern.matches(numRoundsRaw)) {
            throw IllegalArgumentException("라운드 횟수는 양의 정수여야 합니다.")
        }
    }

    fun convertToInt(numRoundsRaw: String): Int {
        var numRoundsConverted: Int = 0
        try {
            numRoundsConverted = numRoundsRaw.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("라운드 횟수는 양의 정수여야 합니다.")
        }
        return numRoundsConverted
    }

    fun fetchCars(carsFetched: ArrayList<Car>) {
        cars = carsFetched
    }

    fun startRace() {
        println("\n실행 결과")
        for (i in 1..numRounds) {
            processRound()
        }
    }

    fun processRound() {
        for (car in cars) car.move()
        println("\n")
    }

    fun displayWinners() {
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

    @JvmName("userDefined")
    fun setNumRounds(rounds: Int) {
        this.numRounds = rounds
    }

    @JvmName("userDefined")
    fun getNumRoundsState(): NumRoundsState {
        return this.numRoundsState
    }
}