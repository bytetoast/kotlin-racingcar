package car

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.assertFailsWith
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.example.app.car.Car
import org.example.app.car.DefaultRandomGenerator
import org.example.app.car.RandomGenerator
import org.example.app.IOTest

class CarTest: IOTest() {
    @Test
    fun `4 이상이 나오면 전진해야 합니다`() {
        val mockGenerator = mockk<RandomGenerator>()
        every {
            mockGenerator.generateRandomNumber()
        } returns 5
        val car = Car("move", mockGenerator)
        car.move()
        assertThat(output()).isEqualTo("move : -")
        verify {
            mockGenerator.generateRandomNumber()
        }
    }

    @Test
    fun `4 미만이 나오면 멈춰야 합니다`() {
        val mockGenerator = mockk<RandomGenerator>()
        every {
            mockGenerator.generateRandomNumber()
        } returns 2
        val car = Car("stay", mockGenerator)
        car.move()
        assertThat(output()).isEqualTo("stay :")
        verify {
            mockGenerator.generateRandomNumber()
        }
    }

    @Test
    fun `이름 글자수가 5자를 초과하는 자동차는 생성할 수 없습니다`() {
        assertFailsWith<IllegalArgumentException> {
            val car = Car("123456", DefaultRandomGenerator())
        }
    }

    @Test
    fun `이름 글자수가 0인 자동차는 생성할 수 없습니다`() {
        assertFailsWith<IllegalArgumentException> {
            val car = Car("", DefaultRandomGenerator())
        }
    }
}