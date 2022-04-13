package grep;

import com.mifmif.common.regex.Generex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchWordRegexTest {
    private SearchWordRegex myRegex;
    private Generex generator;

    @Before
    public void Before() {
        generator = new Generex("\\w+");
    }

    private void baseTest() {
        String line = generator.random(1,20);
        assertTrue(myRegex.findWord(line, line));
        assertTrue(myRegex.findWord(line + "3", line));
        assertTrue(myRegex.findWord("abcdefg", "[a-z]+"));
        assertFalse(myRegex.findWord("abcdefg", "[0-9]+"));
        assertFalse(myRegex.findWord("3", "word "));
        assertTrue(myRegex.findWord("word", "word"));

    }

    @Test
    public void testWithIgnoreCase() {
        myRegex = new SearchWordRegex(true);
        this.baseTest();
        assertTrue(myRegex.findWord("AAAAAAAAAA", "[a-z]+"));
        assertTrue(myRegex.findWord("AAAAAAAAAA", "[A-Z]+"));
    }

    @Test
    public void testWithoutIgnoreCase() {
        myRegex = new SearchWordRegex(false);
        this.baseTest();
        assertFalse(myRegex.findWord("AAAAAAAAAA", "[a-z]+"));
        assertTrue(myRegex.findWord("AAAAAAAAAA", "[A-Z]+"));
    }

}
