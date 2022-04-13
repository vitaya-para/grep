package grep;

import com.mifmif.common.regex.Generex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchWordRegexRandomTest {
    private SearchWordRegex myRegex;
    private Generex generator;

    @Before
    public void Before() {
        //generator = new Generex("")
    }

    @Test
    public void ipTest() {
        //from https://digitalfortress.tech/tips/top-15-commonly-used-regex/
        String pattern = "(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";

        myRegex = new SearchWordRegex(false);
        generator = new Generex(pattern);

        String line = generator.random();
        assertTrue(myRegex.findWord(line, pattern));
        assertTrue(myRegex.findWord(line, "[0-9]+"));
        assertTrue(myRegex.findWord(line, "[\\.]"));

        assertFalse(myRegex.findWord(line, "[a-z]+"));
        assertFalse(myRegex.findWord(line, "[!?\\[\\]]+"));
    }

    @Test
    public void emailTest() {
        String pattern = "[a-zA-Z0-9\\.-]{3,20}\\@[a-zA-Z0-9\\.-]{1,20}\\.[a-zA-Z]{2,6}";
        myRegex = new SearchWordRegex(true);
        generator = new Generex(pattern);

        String line = generator.random();
        assertTrue(myRegex.findWord(line, pattern));
        assertTrue(myRegex.findWord(line, "@"));
        assertTrue(myRegex.findWord(line, "[a-zA-Z]"));

        assertFalse(myRegex.findWord(line, "\\+"));
        assertFalse(myRegex.findWord(line, "[\"]+"));
    }

    private void baseTest() {
        generator = new Generex("[a-z]+");
        Generex numGenerator = new Generex("[0-9]+");
        String line = generator.random(1, 20);
        assertTrue(myRegex.findWord(line, line));
        assertTrue(myRegex.findWord(line + generator.random(1, 20), line));
        assertTrue(myRegex.findWord(generator.random(1, 20), "[a-z]+"));
        assertFalse(myRegex.findWord(generator.random(1, 20), "[0-9]+"));
        assertFalse(myRegex.findWord(numGenerator.random(1, 20), generator.random(1, 20)));

        generator = new Generex("[A-Z]+");
    }

    @Test
    public void testWithIgnoreCase() {
        myRegex = new SearchWordRegex(true);
        this.baseTest();
        assertTrue(myRegex.findWord(generator.random(1, 20), "[a-z]+"));
        assertTrue(myRegex.findWord(generator.random(1, 20), "[A-Z]+"));
    }

    @Test
    public void testWithoutIgnoreCase() {
        myRegex = new SearchWordRegex(false);
        this.baseTest();
        assertFalse(myRegex.findWord(generator.random(1, 20), "[a-z]+"));
        assertTrue(myRegex.findWord(generator.random(1, 20), "[A-Z]+"));
    }

}
