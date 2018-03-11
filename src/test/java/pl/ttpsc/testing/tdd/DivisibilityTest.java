package pl.ttpsc.testing.tdd;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class DivisibilityTest {

    Divisibility sut;

    @Before
    public void setUp() {
        sut = new Divisibility();
    }

    @Test
    @Parameters({"25,5", "36,6", "48,4"})
    public void shouldReturnTrueWhenIsDivisible(int nominator, int denominator) throws CantByZeroException {
        assertTrue(sut.isDivisible(nominator, denominator));
    }

    @Test
    @Parameters({"13,5", "17,6", "48,21"})
    public void shouldReturnFalseWhenNotDivisible(int nominator, int denominator) throws CantByZeroException {
        assertFalse(sut.isDivisible(nominator, denominator));
    }

    @Test
    public void shouldThrowExceptionWhenDivisibleNotPossible1() {

        int nominator = 1;
        int denominator = 0;

        assertThatExceptionOfType(CantByZeroException.class)
                .isThrownBy(() -> sut.isDivisible(nominator, denominator))
                .withMessage("Can't by zero!");
    }

    @Test
    public void shouldThrowExceptionWhenDivisibleNotPossible2() {

        int nominator = 1;
        int denominator = 0;

        assertThatThrownBy(() -> sut.isDivisible(nominator, denominator))
                .isInstanceOf(CantByZeroException.class)
                .hasMessageContaining("Can't by zero!");
    }

}