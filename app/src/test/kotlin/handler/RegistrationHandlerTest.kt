package handler

import kotlin.test.assertFailsWith
import kotlin.test.Test
import org.example.app.handler.NamesState
import org.example.app.handler.RegistrationHandler
import org.example.app.IOTest
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.*

class RegistrationHandlerTest : IOTest() {
    @Test
    fun `waitingForNamesSuccessTest`() {
        val handler = RegistrationHandler()
        handler.carNamesState = NamesState.SUCCESS
        val result = handler.waitingForNames()
        assertEquals(false, result, "상태가 SUCCESS로 바뀌었습니다. 결과는 false여야 합니다.")
    }

    @Test
    fun `waitingForNamesWaitingTest`() {
        val handler = RegistrationHandler()
        handler.carNamesState = NamesState.WAITING
        val result = handler.waitingForNames()
        assertEquals(true, result, "상태는 WAITING입니다. 결과는 true여야 합니다.")
    }

    @Test
    fun `matchesFormatTrueTest`() {
        val handler = RegistrationHandler()
        assertDoesNotThrow {
            handler.matchesFormat("tom,green,blair,luke")
        }
    }

    @Test
    fun `matchesFormatExceptionTest`() {
        val handler = RegistrationHandler()
        assertFailsWith<IllegalArgumentException>("This should throw IllegalArgumentException.") {
            handler.matchesFormat("tom,green,blair,")
        }
    }

    @Test
    fun `allNamesSatisfyConstraintsTrueTest`() {
        val handler = RegistrationHandler()
        assertDoesNotThrow {
            handler.allNamesSatisfyConstraints("blair,car1,dean,CPU,egypt")
        }
    }

    @Test
    fun `allNamesSatisfyLengthConstraintsExceptionTest`() {
        val handler = RegistrationHandler()
        assertFailsWith<IllegalArgumentException>("This should throw IllegalArgumentException.") {
            handler.allNamesSatisfyConstraints("tom,green,potter,blair")
        }
    }

    @Test
    fun `allNamesSatisfyUniqueConstraintsTrueTest`() {
        val handler = RegistrationHandler()
        assertDoesNotThrow {
            handler.allNamesSatisfyConstraints("tom,green,blair,luke")
        }
    }

    @Test
    fun `allNamesSatisfyUniqueConstraintsExceptionTest`() {
        val handler = RegistrationHandler()
        assertFailsWith<IllegalArgumentException>("This should throw IllegalArgumentException.") {
            handler.allNamesSatisfyConstraints("tom,green,blair,tom,luke")
        }
    }

    @Test
    fun `validateApplicantsTrueTest`() {
        val handler = RegistrationHandler()
        handler.validateApplicants("car2,10,brown,woods")
        assertEquals(NamesState.SUCCESS, handler.carNamesState, "The result should be SUCCESS.")
    }

    @Test
    fun `validateApplicantsFalseTest`() {
        val handler = RegistrationHandler()
        handler.validateApplicants("connecticut,court,twain")
        val expected = "올바른 형식으로 다시 입력해주세요"
        assertTrue(output().contains(expected))
    }

    @Test
    fun `confirmEntryTrueTest`() {
        val handler = RegistrationHandler()
        handler.confirmEntry(listOf("turke","y","pumpk","in"))
        val expected = "turke,y,pumpk,in"
        var actual = arrayListOf<String>()
        for (car in handler.cars) {
            actual.add(car.getName())
        }
        assertEquals(expected, actual.joinToString(","))
    }
}