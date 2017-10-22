import org.junit.Assert;
import org.junit.Test;

public class StringCleanerTest {

    private StringCleaner cleaner;

    public StringCleanerTest() {
        cleaner = new StringCleaner();
    }

    @Test
    public void testFirstString() {
        String test = "with its powerful tools and dazzling effects,Keynote makes it Easy to create stunning and memorable presentations. ";
        String expected = "With its powerful tools and dazzling effects, keynote makes it easy to create stunning and memorable presentations.";
        String result = cleaner.cleanString(test);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testSecondString() {
        String test = "See Who you ’re working with ... While you’re editing, a separate list lets you quickly see who else is in the presentation.";
        String expected = "See who you’re working with... While you’re editing, a separate list lets you quickly see who else is in the presentation.";
        String result = cleaner.cleanString(test);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testMultipleSentences() {
        String test = "test123. Test 456.test. test0000.test? ";
        String expected = "Test123. Test 456. Test. Test0000. Test?";
        String result = cleaner.cleanString(test);
        Assert.assertEquals(expected, result);
    }
}
