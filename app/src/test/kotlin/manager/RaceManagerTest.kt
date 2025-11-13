package manager

import kotlin.test.assertFailsWith
import kotlin.test.Test
import org.example.app.manager.RaceManager
import org.junit.jupiter.api.assertDoesNotThrow

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
}