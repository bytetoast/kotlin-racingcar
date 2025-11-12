package org.example.app

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class IOTest {
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun init() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun restoreStreams() {
        System.setOut(System.out)
        println(output())
    }

    fun output(): String {
        return outputStreamCaptor.toString().trim()
    }
}