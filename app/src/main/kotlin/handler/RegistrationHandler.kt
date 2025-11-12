package org.example.app.handler

import org.example.app.handler.NamesState
import org.example.app.car.Car

class RegistrationHandler {
    var carNamesState = NamesState.IDLE
    var cars: ArrayList<Car> = arrayListOf()

    fun startRegistration() {
        println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
        carNamesState = NamesState.WAITING
        awaitValidInput()
    }

    fun awaitValidInput() {
        while (waitingForNames()) {
            val namesRaw = readln()
            validateApplicants(namesRaw)
        }
    }

    fun waitingForNames(): Boolean {
        return carNamesState != NamesState.SUCCESS
    }

    fun validateApplicants(namesRaw: String) {
        var carsList: List<String> = arrayListOf()
        try {
            matchesFormat(namesRaw)
            carsList = allNamesSatisfyLengthConstraints(namesRaw)
        } catch (e: IllegalArgumentException) {
            carNamesState = NamesState.ERROR
            println(e.message + "\n올바른 형식으로 다시 입력해주세요.")
            return
        }
        carNamesState = NamesState.SUCCESS
        confirmEntry(carsList)
    }

    fun matchesFormat(namesRaw: String) {
        val namesPattern = Regex("^[a-zA-Z0-9]{1,5}(,[a-zA-Z0-9]{1,5})*\$")
        if (!namesPattern.matches(namesRaw)) {
            throw IllegalArgumentException("이름은 쉼표(,) 기준으로 구분합니다.")
        }
    }

    fun allNamesSatisfyLengthConstraints(namesRaw: String): List<String> {
        val names: List<String> = namesRaw.split(",")
        for (name in names) {
            if (name.length !in 1..5) {
                throw IllegalArgumentException("자동차의 이름은 1자 이상 5자 이하여야 합니다.")
            }
        }
        return names
    }

    fun confirmEntry(names: List<String>) {
        for (name in names) {
            cars.add(Car(name))
        }
    }

    @JvmName("userDefined")
    fun getCars(): ArrayList<Car> {
        try {
            check(carNamesState == NamesState.SUCCESS) { "자동차 이름 리스트 입력이 정상적으로 완료되지 않았습니다." }
        } catch (e: IllegalArgumentException) {
            println(e.message + "\n올바른 형식으로 다시 입력해주세요.")
            awaitValidInput()
        }
        carNamesState = NamesState.IDLE
        return cars
    }
}