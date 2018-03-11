package pl.ttpsc.testing.junit.calculator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class SimpleCalculatorTest {

    static long startTime;

    SimpleCalculator sut;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void beforeClass() {
        startTime = System.currentTimeMillis();
    }

    @Before
    public void setUp() {
        sut = new SimpleCalculator();
    }

    @Test
    public void testWhenAddingPositiveNumbers() {
        //given
        double a = 1.0d;
        double b = 3.5d;
        //when
        double addingResult = sut.add(a, b);
        //then
        assertEquals("message", 4.5d, addingResult, 0.1d);
    }

    @Test
    public void testWhenAddingNegativeNumbers() {
        //given
        double a = -1.0d;
        double b = -3.5d;
        //when
        double addingResult = sut.add(a, b);
        //then
        assertEquals("message", -4.5d, addingResult, 0.1d);
    }

    @Test
    public void testWhenSubtracting() {
        //given
        double a = 1.0d;
        double b = 3.5d;
        //when
        double addingResult = sut.subtract(a, b);
        //then
        assertEquals("message", -2.5d, addingResult, 0.1d);
    }

    @Test
    public void testWhenIntegerIsPrime() {
        //given
        int number = 13;
        //when
        boolean prime = sut.isPrime(number);
        //then
        //    assertEquals("message", true, prime);
        assertTrue("prime is true", prime);
    }


    @Test
    @Parameters({"7", "11", "29"})
    public void testWhenIntegerIsPrimeWithParameters(int number) {
        //given
        //when
        boolean prime = sut.isPrime(number);
        //then
        assertTrue("prime is true", prime);
    }

    @Test
    @Parameters({"6,4,2", "13,2,1", "20,10,0"})
    public void testWhenModuloWithParameters(int nominator, int denominator, int modulo) {
        //given
        //when
        int result = sut.modulo(nominator, denominator);
        //then
        assertEquals("modulo operation", modulo, result);
    }

    @Test
    @Parameters(method = "numbers")
    public void testWhenAddingNumbersWithParamMethod(double a, double b, double expected) {
        //given
        //when
        double actual = sut.add(a, b);
        //then
        assertEquals("message when adding: ", expected, actual, 0.1d);
    }

    public Object[] numbers() {

        return $(
                $(1.0d, 2.5d, 3.5d),
                $(5.4d, 3.4d, 8.8d),
                $(-1.5d, 1.5d, 0.0d)
        );
    }

    @Test
    @Parameters(method = "primes")
    public void testWhenIntegerIsPrimeWithParameters(int number, boolean primeExpected) {
        //given
        //when
        boolean primeActual = sut.isPrime(number);
        //then
        assertEquals(primeExpected, primeActual);
    }

    public Object[] primes() {
        return $(
                $(13, true),
                $(24, false),
                $(97, true)
        );
    }

    @Test
    public void testWhenIntegerIsNotPrime() {
        //given
        int number = 24;
        //when
        boolean prime = sut.isPrime(number);
        //then
        assertFalse("prime is not true", prime);
    }

    @Test(expected = CannotDivideByZeroException.class)
    public void testWhenDividing() throws CannotDivideByZeroException {
        //given
        double a = 1.0d;
        double b = 0.0d;
        //when
        double actual = sut.divide(a, b);
        //then
        assertEquals("dividing ", 0, actual, 0.0001d);

    }

    //Not recommended!!!!!!!!
    @Test
    public void testWhenDividingWithTryCatch() {
        //given
        double a = 1.0d;
        double b = 0.0d;
        //when
        try {
            sut.divide(a, b);
        } catch (Exception ex) {
            //then
            assertEquals(CannotDivideByZeroException.class, ex.getClass());
            assertEquals("Can't by zero!", ex.getMessage());
        }
    }

    @Test
    public void testWhenDividingWithExpectedExceptionRule() throws CannotDivideByZeroException {
        //given
        double a = 1.0d;
        double b = 0.0d;
        //expect
        expectedException.expect(CannotDivideByZeroException.class);
        expectedException.expectMessage("Can't by zero");
        //when
        sut.divide(a, b);

    }

    @Test
    public void testWhenDividingWithAssertJ1() {

        //given
        double a = 1.0d;
        double b = 0.0d;
        //when
        //then
        assertThatExceptionOfType(CannotDivideByZeroException.class)
                .isThrownBy(() -> sut.divide(a, b))
                .withMessage("Can't by zero!");
    }

    @Test
    public void testWhenDividingWithAssertJ2() {

        //given
        double a = 1.0d;
        double b = 0.0d;
        //when
        //then
        assertThatThrownBy(() -> sut.divide(a, b))
                .isInstanceOf(CannotDivideByZeroException.class)
                .hasMessage("Can't by zero!");
    }

    @After
    public void tearDown() {
        sut = null;
    }


    @AfterClass
    public static void afterClass() {
        System.out.println("All tests duration: " + (System.currentTimeMillis() - startTime) + "ms. ");
    }
}