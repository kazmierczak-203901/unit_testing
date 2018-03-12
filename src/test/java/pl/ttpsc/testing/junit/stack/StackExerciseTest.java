package pl.ttpsc.testing.junit.stack;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class StackExerciseTest {
    public static long startTime;
    StackExercise stackut;

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

    @After
    public void tearDown() {
        stackut = null;
    }


    @Before
    public void setUp() {
        stackut = new StackExercise();
    }

    @Test
    public void shouldGetStringOnTop() {
        //given
        String a = "someString";

        //when
        stackut.push(a);

        //then
        try {
            assertEquals(a, stackut.top());
        } catch (StackEmptyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldGetStringsInCorrectOrder() {
        //given
        String a = "firstString";
        String b = "secondString";

        //when
        stackut.push(a);
        stackut.push(b);
        String first = null;
        String second = null;

        try {
            first = stackut.pop();
            second = stackut.pop();
        } catch (StackEmptyException e) {
            e.printStackTrace();
        }

        //then
        assertEquals(a, second);
        assertEquals(b, first);
    }

    @Test
    //@Ignore
    @Parameters({"some", "diffrent", "strings", "11"})
    public void shouldGetDifferentStrings(String string) {
        //given
        String a = "firstString";

        //when
        stackut.push(a);
        String first = null;

        try {
            first = stackut.pop();
        } catch (StackEmptyException e) {
            e.printStackTrace();
        }

        //then
        assertEquals(string, first);
    }

    @Test
    //@Ignore
    @Parameters(method = "stringFrag")
    public void shouldGetDifferentStrings(String string, boolean stringFrag) {
        //given
        String a = "firstString";

        //when
        stackut.push(a);

        String first = null;
        try {
            first = stackut.pop();
        } catch (StackEmptyException e) {
            e.printStackTrace();
        }

        //then
        assertEquals(stringFrag, string);
    }

    Object[] stringFrag() {
        return $(
                $("I", false),
                $("like", false),
                $("summer")
        );
    }
}
