package org.example.app

class RegistrationHandler {
    var carNamesState = NamesState.IDLE

    fun startRegistration() {
        println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
        carNamesState = NamesState.WAITING
        awaitValidInput()
    }

    fun awaitValidInput() {
        val namesRaw = readln()
        while (waitingForNames()) {
            validateApplicants(namesRaw)
        }
    }

    fun waitingForNames(): Boolean {
        return carNamesState != NamesState.SUCCESS
    }

    fun validateApplicants(namesRaw: String) {
        try {
            matchesFormat(namesRaw)
            allNamesSatisfyLengthConstraints(namesRaw)
        } catch (e: IllegalArgumentException) {
            carNamesState = NamesState.ERROR
            println(e.message + "\n올바른 형식으로 다시 입력해주세요.")
            return
        }
        carNamesState = NamesState.SUCCESS
    }

    fun matchesFormat(namesRaw: String) {
        val namesPattern = Regex("^[a-zA-Z0-9]{1,5}(,[a-zA-Z0-9]{1,5})*\$")
        if (!namesPattern.matches(namesRaw)) {
            throw IllegalArgumentException("이름은 쉼표(,) 기준으로 구분합니다.")
        }
    }

    fun allNamesSatisfyLengthConstraints(namesRaw: String) {
        val names = namesRaw.split(",")
        for (name in names) {
            if (name.length !in 1..5) {
                throw IllegalArgumentException("자동차의 이름은 1자 이상 5자 이하여야 합니다.")
            }
        }
    }
}
