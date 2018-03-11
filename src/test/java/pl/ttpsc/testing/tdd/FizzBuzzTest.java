package pl.ttpsc.testing.tdd;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class FizzBuzzTest {

    FizzBuzz sut;

    @Before
    public void setUp() {
        sut = new FizzBuzz();
    }

    @Test
    @Parameters({"3", "9", "12", "18"})
    public void shouldReturnFizz(int number) {
        assertEquals("Fizz", sut.getFizzBuzzNumber(number));
    }

    @Test
    @Parameters({"5", "10", "20", "35"})
    public void shouldReturnBuzz(int number) {
        assertEquals("Buzz", sut.getFizzBuzzNumber(number));
    }

    @Test
    @Parameters({"15", "30", "120", "180"})
    public void shouldReturnFizzBuzz(int number) {
        assertEquals("FizzBuzz", sut.getFizzBuzzNumber(number));
    }

}