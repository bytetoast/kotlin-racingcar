package manager

import org.example.app.manager.NumRoundsState
import kotlin.test.assertFailsWith
import kotlin.test.Test
import org.example.app.manager.RaceManager
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertEquals

class RaceManagerTest {
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
}