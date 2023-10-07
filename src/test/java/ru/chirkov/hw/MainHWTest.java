package ru.chirkov.hw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainHWTest {

    private MainHW test;
    @BeforeEach
    void setUp() {
        test = new MainHW();
    }

    @AfterEach
    void tearDown() {
        System.out.println("test MainHWTest is over");
    }

    @Test
    void evenOddNumber() {
        assertTrue(test.evenOddNumber(100));
        assertFalse(test.evenOddNumber(7));
    }

    @Test
    void hasBetweenNum() {
        assertFalse(test.isBetweenNumbers(24));
        assertFalse(test.isBetweenNumbers(100));
        assertFalse(test.isBetweenNumbers(1000));
        assertTrue(test.isBetweenNumbers(30));
    }
}