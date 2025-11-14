package manager

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.example.app.car.Car
import org.example.app.car.RandomGenerator
import org.example.app.IOTest
import org.example.app.manager.NumRoundsState
import org.example.app.manager.RaceManager
import org.junit.jupiter.api.assertDoesNotThrow

class RaceManagerTest : IOTest() {
    @Test
    fun `matchesNumFormatTrueTest`() {
        val manager = RaceManager()
        assertDoesNotThrow {
            manager.matchesNumFormat("11")
        }
    }

    @Test
    fun `matchesNumFormatFailsTest`() {
        val manager = RaceManager()
        assertFailsWith<IllegalArgumentException>("This should throw IllegalArgumentException.") {
            manager.matchesNumFormat("-3")
        }
    }

    @Test
    fun `convertToIntTrueTest`() {
        val manager = RaceManager()
        assertDoesNotThrow {
            manager.convertToInt("5")
        }
    }

    @Test
    fun `올바른 입력값이면 상태가 SUCCESS로 바뀌어야 합니다`() {
        val manager = RaceManager()
        manager.validateNumRounds("20")
        assertEquals(NumRoundsState.SUCCESS, manager.numRoundsState)
    }

    @Test
    fun `잘못된 입력값이면 상태가 ERROR로 바뀌어야 합니다`() {
        val manager = RaceManager()
        manager.validateNumRounds("!0")
        assertEquals(NumRoundsState.ERROR, manager.numRoundsState)
    }

    @Test
    fun `가장 많이 이동한 자동차의 이름이 출력되어야 합니다`() {
        val mockAdvance = mockk<RandomGenerator>()
        val mockPaused = mockk<RandomGenerator>()
        val blairAdvance = Car("blair", mockAdvance)
        val mayaAdvance = Car("maya", mockAdvance)
        val tomPaused = Car("tom", mockPaused)
        val alexPaused = Car("alex", mockPaused)
        val manager = RaceManager()

        every {
            mockAdvance.generateRandomNumber()
        } returns 7
        every {
            mockPaused.generateRandomNumber()
        } returns 2
        manager.fetchCars(arrayListOf(blairAdvance, mayaAdvance, tomPaused, alexPaused))
        manager.setNumRounds(5)
        manager.startRace()
        manager.displayWinners()
        assertThat(output()).isEqualTo("실행 결과\n" +
                "blair : -\n" +
                "maya : -\n" +
                "tom : \n" +
                "alex : \n" +
                "\n" +
                "\n" +
                "blair : --\n" +
                "maya : --\n" +
                "tom : \n" +
                "alex : \n" +
                "\n" +
                "\n" +
                "blair : ---\n" +
                "maya : ---\n" +
                "tom : \n" +
                "alex : \n" +
                "\n" +
                "\n" +
                "blair : ----\n" +
                "maya : ----\n" +
                "tom : \n" +
                "alex : \n" +
                "\n" +
                "\n" +
                "blair : -----\n" +
                "maya : -----\n" +
                "tom : \n" +
                "alex : \n" +
                "\n" +
                "\n" +
                "최종 우승자 : blair, maya")
        verify {
            mockAdvance.generateRandomNumber()
        }
    }
}