package pl.ttpsc.testing.junit.calculator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class SimpleCalculatorTest {

    public static long startTime;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    SimpleCalculator sut;

    @BeforeClass
    public static void beforeClass() {
        startTime = System.currentTimeMillis();
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Total time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    static Object[] primeNumbers() {
        return $(
                $(12, false),
                $(7, true),
                $(15, false),
                $(3, true)
        );
    }


    @Before
    public void setUp() {
        sut = new SimpleCalculator();
    }

    @Test
    public void shouldAddTwoNumbers() {
        //given
        double a = 1.0;
        double b = 9.0;

        //when
        double actual = sut.add(a, b);

        //then
        assertEquals(10.0, actual, 0.0000001);
    }

    @Test
    public void shouldSubtractTwoNumbers() {
        //given
        double a = 3.0;
        double b = 1.0;

        //when
        double actual = sut.subtract(a, b);

        //then
        assertEquals("Some assertion error", 2.0, actual, 0.000001);
    }

    @Test
    //@Ignore
    @Parameters({"1", "3", "7", "11"})
    public void shouldDetermineWhenNumberIsPrime(int number) {
        //given

        //when
        boolean prime = sut.isPrime(number);

        //then
        assertTrue(prime);
    }

    @Test
    //@Ignore
    @Parameters(method = "primeNumbers")
    public void shouldDetermineWhenNumberIsPrime(int number, boolean primeFrag) {
        //given

        //when
        boolean prime = sut.isPrime(number);

        //then
        assertEquals(primeFrag, prime);
    }

    @Test(expected = CannotDivideByZeroException.class)
    public void shouldNotDivideByZero() throws CannotDivideByZeroException {
        //given
        double a = 2.0;
        double b = 0.0;

        //when
        sut.divide(a, b);
    }

    @Test
    public void shouldNotDivideByZeroWithExpected() throws CannotDivideByZeroException {
        //given
        double a = 2.0;
        double b = 0.0;

        //expect
        expectedException.expect(CannotDivideByZeroException.class);

        //when
        sut.divide(a, b);
    }

    @Test
    public void shouldNotDivideByZeroWithAssert() {

        //given
        double a = 2.0;
        double b = 0.0;

        //expect
        assertThatExceptionOfType(CannotDivideByZeroException.class)
                .isThrownBy(() -> sut.divide(a, b));

        //when
        assertThatThrownBy(() -> sut.divide(a, b))
                .isInstanceOf(CannotDivideByZeroException.class);

    }

    @After
    public void tearDown() {
        sut = null;
    }
}