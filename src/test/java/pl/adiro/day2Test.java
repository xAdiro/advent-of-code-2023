package pl.adiro;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class day2Test {
    @Test
    void part1() throws FileNotFoundException {
        assertEquals(8, day2.part1("src/main/resources/day2/test.txt"));
    }
}