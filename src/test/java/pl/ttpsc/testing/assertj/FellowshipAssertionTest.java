package pl.ttpsc.testing.assertj;

import org.junit.Test;

import static pl.ttpsc.testing.assertj.FellowTestFixture.*;

public class FellowshipAssertionTest {

    private Fellowship fellowship = formTheFellowshipOfTheRing();

    @Test
    public void frodoShouldBeIn() {
    }

    @Test
    public void sauronShouldNotBeIn() {
    }

    @Test
    public void shouldFrodoNameBeCorrectAndNotBeASauronAndBeInFellowship() {
    }

    @Test
    public void aragornShouldBeBeforeBoromir() {
    }

    @Test
    public void shouldNotContainDuplicatedFellows() {
    }

    @Test
    public void shouldContainOnlyFellowsWithExpectedNamesInProperOrder() {
    }

    @Test
    public void shouldContainNineFellowsButNoneGiant() {
    }

    @Test
    public void shouldContainTupleForBoromirSamAndLegolas() {
    }


    @Test
    public void shouldContainsFourHobbits() {
    }


    private Fellowship formTheFellowshipOfTheRing() {
        return new Fellowship(
                frodo(),
                sam(),
                merry(),
                pippin(),
                gandalf(),
                legolas(),
                gimli(),
                aragorn(),
                boromir());
    }
}
