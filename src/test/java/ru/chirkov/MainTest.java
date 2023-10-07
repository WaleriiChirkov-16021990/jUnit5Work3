package ru.chirkov;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private final PrintStream out = System.out;
    private ByteArrayOutputStream outStream;

    @BeforeEach
    void init() throws Exception {
        outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
    }


    private String getOutput() {
        return outStream.toString();
    }


    @Test
    void mainTest() {
        String test = "Welcome to hell\r\n";
        Main.main(new String[0]);
        assertEquals(test, getOutput());
    }

    @AfterEach
    void restoreSystemOutput() {
        System.setOut(out);
    }
}